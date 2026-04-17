package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.repository.DossierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
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
    void findAll_shouldReturnAllDossiers() {
        Dossier d1 = new Dossier();
        d1.setId(1L);

        Dossier d2 = new Dossier();
        d2.setId(2L);

        when(dossierRepository.findAll()).thenReturn(List.of(d1, d2));

        List<Dossier> result = dossierService.findAll();

        assertEquals(2, result.size());
        verify(dossierRepository).findAll();
    }

    @Test
    void findById_shouldReturnDossierWhenFound() {
        Dossier dossier = new Dossier();
        dossier.setId(1L);

        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        Optional<Dossier> result = dossierService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(1L, result.get().getId());
        verify(dossierRepository).findById(1L);
    }

    @Test
    void findById_shouldReturnEmptyWhenNotFound() {
        when(dossierRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Dossier> result = dossierService.findById(99L);

        assertTrue(result.isEmpty());
        verify(dossierRepository).findById(99L);
    }

    @Test
    void updateStatut_shouldUpdateDossierStatus() {
        Dossier dossier = new Dossier();
        dossier.setId(1L);
        dossier.setStatut(StatutDossier.EN_ATTENTE_DOCUMENTS);

        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        dossierService.updateStatut(1L, "ACCEPTE");

        assertEquals(StatutDossier.ACCEPTE, dossier.getStatut());
        verify(dossierRepository).findById(1L);
        verify(dossierRepository).save(dossier);
    }

    @Test
    void updateStatut_shouldThrowWhenDossierNotFound() {
        when(dossierRepository.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                dossierService.updateStatut(99L, "REFUSE")
        );

        assertEquals("Dossier non trouvé", exception.getMessage());
        verify(dossierRepository).findById(99L);
        verify(dossierRepository, never()).save(any());
    }

    @Test
    void updateStatut_shouldThrowWhenStatutIsInvalid() {
        Dossier dossier = new Dossier();
        dossier.setId(1L);
        dossier.setStatut(StatutDossier.EN_ATTENTE_DOCUMENTS);

        when(dossierRepository.findById(1L)).thenReturn(Optional.of(dossier));

        assertThrows(IllegalArgumentException.class, () ->
                dossierService.updateStatut(1L, "STATUT_INVALIDE")
        );

        verify(dossierRepository).findById(1L);
        verify(dossierRepository, never()).save(any());
    }
}