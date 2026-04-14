package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class InscriptionController {

    private final UserService userService;

    public InscriptionController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/formulaire-inscription")
    public String afficherFormulaireInscription(Model model) {
        model.addAttribute("user", new User());
        return "inscription";
    }

    @PostMapping("/formulaire-inscription")
    public String inscrireUtilisateur(
            @Valid @ModelAttribute("user") User user,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "inscription";
        }

        try {
         userService.registerUser(user.getPrenom(), user.getNom(), user.getEmail(), user.getPassword()); // CORRECT
            model.addAttribute("success", "Inscription réussie ! Vous pouvez maintenant vous connecter.");
            return "login";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", "Cette adresse email est déjà utilisée.");
            return "inscription";
        }
    }
}