package br.agrimedi.agrimediweb.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import br.agrimedi.agrimediweb.entity.Usuario;
import br.agrimedi.agrimediweb.repository.UsuarioRepository;

@Controller
public class LoginController {
    
    @Autowired
    private UsuarioRepository repository;
    
    @GetMapping("/login")
    public ModelAndView login(HttpSession session) {
        if (session.getAttribute("usuarioLogado") != null) {
            return new ModelAndView("redirect:/clientes");
        }
        return new ModelAndView("login/login", "usuario", new Usuario());
    }
    
    @PostMapping("/login")
    public ModelAndView doLogin(Usuario usuario, HttpSession session) {
        var usuarioEncontrado = repository.findByUsernameAndPassword(
            usuario.getUsername(), 
            usuario.getPassword()
        );
        
        if (usuarioEncontrado != null) {
            session.setAttribute("usuarioLogado", usuarioEncontrado);
            return new ModelAndView("redirect:/clientes");
        }
        
        return new ModelAndView("redirect:/login?error=true");
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
