package br.com.estanteweb;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("/livros/novo")
    public String formularioLivro() {
        return "livro-novo";
    }

    @PostMapping("/livros")
    public String cadastrarLivro(@RequestParam String titulo,
                                @RequestParam String autor,
                                @RequestParam int ano,
                                @RequestParam double preco,
                                Model model) {
        String erro = null;
        if (titulo.isBlank() || autor.isBlank()) {
            erro = "Título e autor não podem ficar em branco.";
        } else if (preco <= 0) {
            erro = "O preço deve ser maior que zero.";
        }

        if (erro != null) {
            model.addAttribute("erro", erro);
            model.addAttribute("titulo", titulo);
            model.addAttribute("autor", autor);
            model.addAttribute("ano", ano);
            model.addAttribute("preco", preco);
            return "livro-novo";
        }

        Livro livro = new Livro();
        livro.setTitulo(titulo);
        livro.setAutor(autor);
        livro.setAno(ano);
        livro.setPreco(preco);
        repository.salvar(livro);
        return "redirect:/livros/html";
    }

    @ResponseBody
    @GetMapping(value = "/livros", produces = "text/plain")
    public String listarLivros() {
        List<Livro> livros = repository.listarTodos();

        StringBuilder resposta = new StringBuilder("Acervo EstanteWeb: ");
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            resposta.append(l.getTitulo())
                    .append(", de ")
                    .append(l.getAutor())
                    .append(" (R$ ")
                    .append(l.getPreco())
                    .append(")");
            if (i < livros.size() - 1) {
                resposta.append(" | ");
            }
        }
        return resposta.toString();
    }

    @ResponseBody
    @GetMapping("/livros/total")
    public String totalLivros() {
        return "O acervo tem " + repository.contarLivros() + " livros.";
    }

    @GetMapping("/livros/html")
    public String listarLivrosHtml(@RequestParam(required = false) String ordem, Model model) {
        model.addAttribute("livros", ordem == null ? repository.listarTodos() : repository.listarOrdenadoPor(ordem));
        return "livros";
    }

    @GetMapping("/livros/{id}")
    public String detalheLivro(@PathVariable Long id, Model model) {
        Livro livro = repository.buscarPorId(id);
        if (livro == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("livro", livro);
        return "livro";
    }

    @GetMapping(value = "/livros/busca", produces = "text/plain")
    public ResponseEntity<String> buscarPorPreco(@RequestParam(defaultValue = "999") double precoMaximo) {
        List<Livro> livros = repository.listarAtePreco(precoMaximo);

        if (livros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nenhum livro encontrado até R$ " + precoMaximo);
        }

        StringBuilder resposta = new StringBuilder("Acervo EstanteWeb: ");
        for (int i = 0; i < livros.size(); i++) {
            Livro l = livros.get(i);
            resposta.append(l.getTitulo())
                    .append(", de ")
                    .append(l.getAutor())
                    .append(" (R$ ")
                    .append(l.getPreco())
                    .append(")");
            if (i < livros.size() - 1) {
                resposta.append(" | ");
            }
        }

        return ResponseEntity.ok()
                .header("X-Total-Encontrados", String.valueOf(livros.size()))
                .body(resposta.toString());
    }

    @ResponseBody
    @GetMapping("/livros/valor-total")
    public String valorTotalAcervo() {
        return "Valor total do acervo: R$ " + repository.somarValorAcervo();
    }

    @ResponseBody
    @GetMapping("/livros/quem-acessa")
    public String quemAcessa(@RequestHeader("User-Agent") String userAgent) {
        return "Você acessou usando: " + userAgent;
    }
}