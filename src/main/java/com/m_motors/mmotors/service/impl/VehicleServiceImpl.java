package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import com.m_motors.mmotors.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Indique à Spring que c'est un service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;

    @Autowired // Injection de dépendance via le constructeur
    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<Vehicle> getAllVehicules() {
        return vehicleRepository.findAll();
    }

    @Override
    public Optional<Vehicle> getVehiculeById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public Vehicle saveVehicule(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicule(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    public List<Vehicle> rechercherVehicules(String marque, String modele, TypeOffre typeOffre) {
        // Implémente la logique de recherche ici. C'est un exemple simple.
        // Tu devras peut-être ajouter des méthodes de recherche personnalisées dans VehicleRepository
        // ou utiliser Spring Data JPA Specifications.
        if (marque != null && !marque.isEmpty() && modele != null && !modele.isEmpty() && typeOffre != null) {
            return vehicleRepository.findByMarqueContainingIgnoreCaseAndModeleContainingIgnoreCaseAndTypeOffre(marque, modele, typeOffre);
        } else if (marque != null && !marque.isEmpty() && modele != null && !modele.isEmpty()) {
            return vehicleRepository.findByMarqueContainingIgnoreCaseAndModeleContainingIgnoreCase(marque, modele);
        } else if (marque != null && !marque.isEmpty()) {
            return vehicleRepository.findByMarqueContainingIgnoreCase(marque);
        } else if (modele != null && !modele.isEmpty()) {
            return vehicleRepository.findByModeleContainingIgnoreCase(modele);
        } else if (typeOffre != null) {
            return vehicleRepository.findByTypeOffre(typeOffre);
        }
        return vehicleRepository.findAll(); // Retourne tout si aucun critère
    }
}