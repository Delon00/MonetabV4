package ci.digitalacademy.monetab.controller;

import ci.digitalacademy.monetab.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    public String index(Model model) {
        return "auth/login";
    }

    @PostMapping("login")
    public String loginSubmit(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            model.addAttribute("error", "Veuillez remplir tous les champs.");
            return "auth/login";
        }

        if ("admin".equals(username) && "admin".equals(password)) {
            return "redirect:/home";
        } else {
            model.addAttribute("error", "Identifiant ou Mot de passe incorrect.");
            return "auth/login";
        }
    }

    //@PostMapping("auth/register")
    //public String registerSubmit(){}


}
