package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/inscription")
    public String showInscriptionPage(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }
    @GetMapping("/client/dashboard")
public String clientDashboard(Model model) {
    model.addAttribute("title", "Mon Espace Client");
    return "client/dashboard";
}

    @GetMapping("/register")
    public String showRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @GetMapping("/formulaire-inscription")
public String showFormulaireInscriptionPage(Model model) {
    model.addAttribute("user", new User());
    return "inscription";
}

    @PostMapping("/register")
    public String registerUser(
            @RequestParam("prenom") String prenom,
            @RequestParam("nom") String nom,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model
    ) {
        if (!password.equals(confirmPassword)) {
            return "redirect:/inscription?error=password_mismatch";
        }

        try {
            userService.registerUser(prenom, nom, email, password);
            return "redirect:/login?success=registered";
        } catch (Exception e) {
            return "redirect:/inscription?error=email_exists";
        }
    }
}