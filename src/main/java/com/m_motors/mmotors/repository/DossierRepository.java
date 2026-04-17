package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DossierRepository extends JpaRepository<Dossier, Long> {
    List<Dossier> findByClientId(Long clientId);
}