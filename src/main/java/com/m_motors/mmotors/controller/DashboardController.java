package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.service.DossierService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DashboardController {

    private final DossierService dossierService;

    public DashboardController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin/dashboard";
    }

    @GetMapping("/admin/dossiers")
    public String adminDossiers(Model model) {
        model.addAttribute("dossiers", dossierService.findAll());
        return "admin/dossiers";
    }

    @GetMapping("/admin/dossiers/{id}")
    public String detailDossierAdmin(@PathVariable Long id, Model model) {
        Dossier dossier = dossierService.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));

        model.addAttribute("dossier", dossier);
        model.addAttribute("statuts", StatutDossier.values());
        return "admin/detail-dossier";
    }

    @PostMapping("/admin/dossiers/{id}/statut")
    public String updateStatutDossier(@PathVariable Long id,
                                      @RequestParam("statut") String statut) {
        dossierService.updateStatut(id, statut);
        return "redirect:/admin/dossiers/" + id;
    }
}