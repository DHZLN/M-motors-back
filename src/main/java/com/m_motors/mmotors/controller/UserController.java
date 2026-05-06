package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 👉 PAGE MOT DE PASSE OUBLIÉ
    @GetMapping("/mot-de-passe-oublie")
    public String forgotPasswordPage() {
        return "mot-de-passe-oublie";
    }

    // 👉 ENVOI EMAIL RESET
    @PostMapping("/mot-de-passe-oublie")
    public String handleForgotPassword(@RequestParam String email) {
        userService.createPasswordResetToken(email);
        return "redirect:/mot-de-passe-oublie?success";
    }

    // 👉 PAGE RESET PASSWORD
    @GetMapping("/reset-password")
    public String showResetPage(@RequestParam String token, Model model) {
        model.addAttribute("token", token);
        return "reset-password";
    }

    // 👉 RESET PASSWORD
    @PostMapping("/reset-password")
    public String resetPassword(
            @RequestParam String token,
            @RequestParam String password,
            @RequestParam String confirmPassword
    ) {
        if (!password.equals(confirmPassword)) {
            return "redirect:/reset-password?token=" + token + "&error";
        }

        userService.resetPassword(token, password);

        return "redirect:/login?resetSuccess";
    }
}