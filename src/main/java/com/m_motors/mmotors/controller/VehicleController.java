package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping("/vehicules")
    public String listVehicles(Model model) {
        List<Vehicle> vehicules = vehicleRepository.findAll();
        model.addAttribute("vehicules", vehicules);
        return "vehicules/liste";
    }
}