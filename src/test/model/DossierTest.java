package com.m_motors.mmotors.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DossierTest {

    @Test
    public void testDossierGettersAndSetters() {
        // Création d'un dossier
        Dossier dossier = new Dossier();
        dossier.setId(1L);
        dossier.setNom("Dossier Test");
        dossier.setDescription("Description du dossier test");

        // Vérification des getters
        assertEquals(1L, dossier.getId());
        assertEquals("Dossier Test", dossier.getNom());
        assertEquals("Description du dossier test", dossier.getDescription());
    }

    @Test
    public void testDossierDefaultConstructor() {
        // Test du constructeur par défaut
        Dossier dossier = new Dossier();
        assertNull(dossier.getId());
        assertNull(dossier.getNom());
        assertNull(dossier.getDescription());
    }

    @Test
    public void testDossierAllArgsConstructor() {
        // Test du constructeur avec tous les arguments
        Dossier dossier = new Dossier(2L, "Autre Dossier", "Autre description");
        assertEquals(2L, dossier.getId());
        assertEquals("Autre Dossier", dossier.getNom());
        assertEquals("Autre description", dossier.getDescription());
    }

    @Test
    public void testDossierEqualsAndHashCode() {
        // Test des méthodes equals et hashCode
        Dossier dossier1 = new Dossier(1L, "Dossier 1", "Description 1");
        Dossier dossier2 = new Dossier(1L, "Dossier 1", "Description 1");
        Dossier dossier3 = new Dossier(2L, "Dossier 2", "Description 2");

        assertEquals(dossier1, dossier2);
        assertNotEquals(dossier1, dossier3);
        assertEquals(dossier1.hashCode(), dossier2.hashCode());
    }
}