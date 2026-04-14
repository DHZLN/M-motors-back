package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.service.UserService;

public class UserController { // Pas @Controller pour l'instant
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public String showRegistrationForm() {
        return "inscription";
    }

    public String registerUser(User user) {
       userService.registerUser(user.getPrenom(), user.getNom(), user.getEmail(), user.getPassword()); // CORRECT
        return "inscription-success"; // Ou redirection vers connexion
    }

    public String showLoginForm() {
        return "connexion";
    }
}