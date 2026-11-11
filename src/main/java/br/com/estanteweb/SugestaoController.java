package br.com.estanteweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}