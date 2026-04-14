package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    List<Vehicle> getAllVehicules();
    Optional<Vehicle> getVehiculeById(Long id); // Utilisez Long pour les IDs
    Vehicle saveVehicule(Vehicle vehicle);
    void deleteVehicule(Long id);
    
    List<Vehicle> rechercherVehicules(String marque, String modele, TypeOffre typeOffre);
    // Ajoutez d'autres variantes de rechercherVehicules si nécessaire, par exemple:
    // List<Vehicle> rechercherVehicules(String marque, String modele);
    // List<Vehicle> rechercherVehicules(String marque);
}