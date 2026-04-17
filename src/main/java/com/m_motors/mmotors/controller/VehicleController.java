package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

 @GetMapping("/vehicules")
public String listVehicles(
        @RequestParam(value = "marque", required = false) String marque,
        @RequestParam(value = "modele", required = false) String modele,
        @RequestParam(value = "typeOffre", required = false) String typeOffre,
        @RequestParam(value = "budgetMax", required = false) Double budgetMax,
        Model model) {

    List<Vehicle> vehicules = vehicleRepository.findAll();

    if (marque != null && !marque.isBlank()) {
        String marqueLower = marque.toLowerCase();
        vehicules = vehicules.stream()
                .filter(v -> v.getMarque() != null && v.getMarque().toLowerCase().contains(marqueLower))
                .collect(Collectors.toList());
    }

    if (modele != null && !modele.isBlank()) {
        String modeleLower = modele.toLowerCase();
        vehicules = vehicules.stream()
                .filter(v -> v.getModele() != null && v.getModele().toLowerCase().contains(modeleLower))
                .collect(Collectors.toList());
    }

    TypeOffre typeOffreEnum = null;
    if (typeOffre != null && !typeOffre.isBlank()) {
        try {
            typeOffreEnum = TypeOffre.valueOf(typeOffre);

            TypeOffre finalTypeOffreEnum = typeOffreEnum;
            vehicules = vehicules.stream()
                    .filter(v -> (finalTypeOffreEnum == TypeOffre.ACHAT && v.getPrixAchat() != null)
                            || (finalTypeOffreEnum == TypeOffre.LLD && v.getLoyerMensuel() != null))
                    .collect(Collectors.toList());

        } catch (IllegalArgumentException e) {
            typeOffreEnum = null;
        }
    }

    if (budgetMax != null) {
        vehicules = vehicules.stream()
                .filter(v ->
                        (v.getPrixAchat() != null && v.getPrixAchat() <= budgetMax)
                                || (v.getLoyerMensuel() != null && v.getLoyerMensuel() <= budgetMax))
                .collect(Collectors.toList());
    }

    model.addAttribute("vehicules", vehicules);
    model.addAttribute("marque", marque);
    model.addAttribute("modele", modele);
    model.addAttribute("typeOffre", typeOffreEnum);
    model.addAttribute("budgetMax", budgetMax);

    return "vehicules/liste";
}

    @GetMapping("/vehicules/{id}")
    public String detailVehicule(@PathVariable Long id, Model model) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findById(id);

        if (vehicleOpt.isEmpty()) {
            return "redirect:/vehicules";
        }

        model.addAttribute("vehicule", vehicleOpt.get());
        return "vehicules/detail";
    }
}