package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Controller
public class SugestaoController {

    @Autowired
    private SugestaoRepository repository;

    @GetMapping("/sugestoes/nova")
    public String formularioSugestao() {
        return "sugestao-nova";
    }

    @PostMapping("/sugestoes")
    public String receberSugestao(@RequestParam String nomeCliente,
                                    @RequestParam String livroSugerido,
                                    Model model) {
        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Nome e livro sugerido não podem ficar em branco.");
            model.addAttribute("nomeCliente", nomeCliente);
            model.addAttribute("livroSugerido", livroSugerido);
            return "sugestao-nova";
        }

        repository.salvar(new Sugestao(nomeCliente, livroSugerido));
        return "redirect:/sugestoes?enviada=true";
    }

    @GetMapping("/sugestoes")
    public String listarSugestoes(@RequestParam(required = false) Boolean enviada, Model model) {
        model.addAttribute("sugestoes", repository.listarTodas());
        model.addAttribute("enviada", enviada);
        return "sugestoes";
    }

    @GetMapping("/sugestoes/{id}/editar")
    public String formularioEdicao(@PathVariable Long id, Model model) {
        model.addAttribute("sugestao", sugestaoExistente(id));
        return "sugestao-editar";
    }

    @PostMapping("/sugestoes/{id}/editar")
    public String editarSugestao(@PathVariable Long id, @RequestParam String nomeCliente,
                                  @RequestParam String livroSugerido, Model model) {
        Sugestao sugestao = new Sugestao(nomeCliente, livroSugerido);
        sugestao.setId(id);
        if (nomeCliente.isBlank() || livroSugerido.isBlank()) {
            model.addAttribute("erro", "Nome e livro sugerido não podem ficar em branco.");
            model.addAttribute("sugestao", sugestao);
            model.addAttribute("nomeInformado", nomeCliente);
            model.addAttribute("livroInformado", livroSugerido);
            return "sugestao-editar";
        }
        if (repository.atualizar(sugestao) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return "redirect:/sugestoes";
    }

    @GetMapping("/sugestoes/{id}/remover")
    public String confirmarRemocao(@PathVariable Long id, Model model) {
        model.addAttribute("sugestao", sugestaoExistente(id));
        return "sugestao-remover";
    }

    @PostMapping("/sugestoes/{id}/remover")
    public String removerSugestao(@PathVariable Long id) {
        if (repository.remover(id) == 0) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return "redirect:/sugestoes";
    }

    private Sugestao sugestaoExistente(Long id) {
        Sugestao sugestao = repository.buscarPorId(id);
        if (sugestao == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return sugestao;
    }
}
