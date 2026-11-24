package br.com.estanteweb;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/login")
    public String formularioLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha,
                        Model model, HttpSession session) {
        Usuario usuario = repository.buscarPorEmail(email);
        if (usuario == null || !usuario.getSenha().equals(senha)) {
            model.addAttribute("erro", "E-mail ou senha inválidos.");
            model.addAttribute("email", email);
            return "login";
        }
        session.setAttribute("usuarioLogado", usuario);
        return "redirect:/livros/html";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/login";
    }
}
