package com.m_motors.mmotors.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController { // Ou AdminController, selon ton organisation

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    // Nouvelle méthode pour gérer les dossiers admin
    @GetMapping("/admin/dossiers")
    public String adminDossiers() {
        // Ici, tu pourrais récupérer la liste des dossiers depuis un service
        // model.addAttribute("dossiers", dossierService.findAll());
        return "admin/dossiers"; // Ceci correspondra à ton fichier admin/dossiers.html
    }
}