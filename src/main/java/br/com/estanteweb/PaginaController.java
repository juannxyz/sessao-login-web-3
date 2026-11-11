package br.com.estanteweb;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PaginaController {

    @ResponseBody
    @GetMapping("/")
    public String home() {
        return "EstanteWeb: livros usados com história";
    }

    @ResponseBody
    @GetMapping("/sobre")
    public String sobre() {
        return "A EstanteWeb é um sebo de bairro que vende livros usados com muita história.";
    }

    @GetMapping("/contato")
    public String formularioContato(Model model) {
        return "contato";
    }
    
    @GetMapping("/contato/sucesso")
    public String sucessoContato() {
        return "contato-sucesso";
}

    @PostMapping("/contato")
    public String receberContato(@RequestParam String nome, @RequestParam String mensagem, Model model) {
    if (mensagem.isBlank()) {
        model.addAttribute("erro", "A mensagem não pode estar em branco.");
        model.addAttribute("nome", nome);
        return "contato";
    }
        return "redirect:/contato/sucesso";
    }
}