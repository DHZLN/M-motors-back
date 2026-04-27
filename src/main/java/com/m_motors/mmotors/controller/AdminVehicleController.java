package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/vehicules")
public class AdminVehicleController {

    private final VehicleService vehicleService;

    public AdminVehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping
    public String listVehicules(Model model) {
        model.addAttribute("vehicules", vehicleService.getAllVehicules());
        return "admin/vehicules";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("typesOffre", TypeOffre.values());
        return "admin/vehicule-form";
    }

    @PostMapping("/save")
    public String saveVehicule(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicule(vehicle);
        return "redirect:/admin/vehicules";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehiculeById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("typesOffre", TypeOffre.values());
        return "admin/vehicule-form";
    }

    @PostMapping("/delete/{id}")
    public String deleteVehicule(@PathVariable Long id) {
        vehicleService.deleteVehicule(id);
        return "redirect:/admin/vehicules";
    }
}