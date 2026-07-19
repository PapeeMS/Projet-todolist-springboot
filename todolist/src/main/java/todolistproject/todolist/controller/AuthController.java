package todolistproject.todolist.controller;

import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import todolistproject.todolist.dto.UtilisateurDto;
import todolistproject.todolist.service.UtilisateurService;

@AllArgsConstructor
@Controller
public class AuthController {

    private final UtilisateurService utilisateurService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String showSignupStep1() {
        return "signup1";
    }

    @PostMapping("/signup/step1")
    public String signupStep1(@RequestParam String username, HttpSession session, Model model) {
        if (utilisateurService.usernameExists(username)) {
            model.addAttribute("error", "Ce nom d'utilisateur existe déjà.");
            return "signup1";
        }

        session.setAttribute("signupUsername", username);
        return "redirect:/signup/step2";
    }

    @GetMapping("/signup/step2")
    public String showSignupStep2(HttpSession session, Model model) {
        String username = (String) session.getAttribute("signupUsername");
        if (username == null) {
            return "redirect:/signup";
        }

        model.addAttribute("username", username);
        return "signup2";
    }

    @PostMapping("/signup/step2")
    public String signupStep2(
            @RequestParam String password,
            @RequestParam("password_confirm") String passwordConfirm,
            HttpSession session,
            Model model
    ) {
        String username = (String) session.getAttribute("signupUsername");
        if (username == null) {
            return "redirect:/signup";
        }

        if (!password.equals(passwordConfirm)) {
            model.addAttribute("username", username);
            model.addAttribute("error", "Les mots de passe ne correspondent pas.");
            return "signup2";
        }

        if (utilisateurService.usernameExists(username)) {
            session.removeAttribute("signupUsername");
            model.addAttribute("error", "Ce nom d'utilisateur existe déjà.");
            return "signup1";
        }

        utilisateurService.register(new UtilisateurDto(null, username, password));
        session.removeAttribute("signupUsername");
        return "redirect:/login?registered";
    }
}
