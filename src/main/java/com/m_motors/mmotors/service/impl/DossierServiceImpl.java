package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.repository.DossierRepository;
import com.m_motors.mmotors.service.DossierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;

    public DossierServiceImpl(DossierRepository dossierRepository) {
        this.dossierRepository = dossierRepository;
    }

    @Override
    public List<Dossier> findAll() {
        return dossierRepository.findAll();
    }

    @Override
    public Optional<Dossier> findById(Long id) {
        return dossierRepository.findById(id);
    }

    @Override
    public Dossier save(Dossier dossier) {
        return dossierRepository.save(dossier);
    }

    @Override
    public void deleteById(Long id) {
        dossierRepository.deleteById(id);
    }

    @Override
    public Dossier updateStatut(Long id, String statut) {
        Dossier dossier = dossierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));

        dossier.setStatut(StatutDossier.valueOf(statut));
        return dossierRepository.save(dossier);
    }
}