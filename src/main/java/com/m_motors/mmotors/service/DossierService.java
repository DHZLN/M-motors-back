package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.Dossier;

import java.util.List;
import java.util.Optional;

public interface DossierService {

    List<Dossier> findAll();

    Optional<Dossier> findById(Long id);

    void updateStatut(Long id, String statut);
}