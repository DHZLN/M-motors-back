package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.repository.DossierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DossierServiceImplTest {

    private DossierRepository dossierRepository;
    private DossierServiceImpl dossierService;

    @BeforeEach
    void setUp() {
        dossierRepository = mock(DossierRepository.class);
        dossierService = new DossierServiceImpl(dossierRepository);
    }

    @Test
    void findAll_shouldReturnList() {
        dossierService.findAll();
        verify(dossierRepository).findAll();
    }

    @Test
    void findById_shouldReturnDossier() {
        Dossier dossier = new Dossier();
        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        Optional<Dossier> result = dossierService.findById(1L);

        assertTrue(result.isPresent());
    }

    @Test
    void updateStatut_shouldUpdateStatus() {
        Dossier dossier = new Dossier();
        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        dossierService.updateStatut(1L, "ACCEPTE");

        assertEquals(StatutDossier.ACCEPTE, dossier.getStatut());
        verify(dossierRepository).save(dossier);
    }

    @Test
    void updateStatut_shouldThrowException_whenNotFound() {
        when(dossierRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () ->
                dossierService.updateStatut(1L, "ACCEPTE")
        );
    }
}