package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findByMarqueContainingIgnoreCaseAndModeleContainingIgnoreCaseAndTypeOffre(
        String marque, String modele, TypeOffre typeOffre);

    List<Vehicle> findByMarqueContainingIgnoreCaseAndModeleContainingIgnoreCase(
        String marque, String modele);

    List<Vehicle> findByMarqueContainingIgnoreCase(String marque);

    List<Vehicle> findByModeleContainingIgnoreCase(String modele);

    List<Vehicle> findByTypeOffre(TypeOffre typeOffre);
}