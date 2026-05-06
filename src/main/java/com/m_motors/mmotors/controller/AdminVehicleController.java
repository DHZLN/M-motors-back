package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.VehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/vehicles")
public class AdminVehicleController {

    private final VehicleService vehicleService;

    public AdminVehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    // 👉 LISTE
    @GetMapping
    public String listVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getAllVehicules());
        return "admin/vehicules"; // ton HTML peut garder le nom français
    }

    // 👉 CREATE
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        model.addAttribute("typesOffre", TypeOffre.values());
        return "admin/vehicule-form";
    }

    // 👉 SAVE
    @PostMapping("/save")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle) {
        vehicleService.saveVehicule(vehicle);
        return "redirect:/admin/vehicles";
    }

    // 👉 EDIT
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Vehicle vehicle = vehicleService.getVehiculeById(id)
                .orElseThrow(() -> new RuntimeException("Véhicule introuvable"));

        model.addAttribute("vehicle", vehicle);
        model.addAttribute("typesOffre", TypeOffre.values());
        return "admin/vehicule-form";
    }

    // 👉 DELETE
    @PostMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        vehicleService.deleteVehicule(id);
        return "redirect:/admin/vehicles";
    }
}