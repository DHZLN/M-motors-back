package com.m_motors.mmotors.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testUserGettersAndSetters() {
        // Création d'un utilisateur
        User user = new User();
        user.setId(1L);
        user.setEmail("helene.leleux@sncf.fr");
        user.setNom("Leleux");
        user.setPrenom("Hélène");
        user.setPassword("securePassword123");
        user.setRole("ADMIN");
        user.setEnabled(true);

        // Vérification des getters
        assertEquals(1L, user.getId());
        assertEquals("helene.leleux@sncf.fr", user.getEmail());
        assertEquals("Leleux", user.getNom());
        assertEquals("Hélène", user.getPrenom());
        assertEquals("securePassword123", user.getPassword());
        assertEquals("ADMIN", user.getRole());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testUserDefaultConstructor() {
        // Test du constructeur par défaut
        User user = new User();
        assertNull(user.getId());
        assertNull(user.getEmail());
        assertNull(user.getNom());
        assertNull(user.getPrenom());
        assertNull(user.getPassword());
        assertNull(user.getRole());
        assertFalse(user.isEnabled());
    }

    @Test
    public void testUserAllArgsConstructor() {
        // Test du constructeur avec tous les arguments
        User user = new User(2L, "jean.dupont@sncf.fr", "Dupont", "Jean", "password123", "CLIENT", true);
        assertEquals(2L, user.getId());
        assertEquals("jean.dupont@sncf.fr", user.getEmail());
        assertEquals("Dupont", user.getNom());
        assertEquals("Jean", user.getPrenom());
        assertEquals("password123", user.getPassword());
        assertEquals("CLIENT", user.getRole());
        assertTrue(user.isEnabled());
    }

    @Test
    public void testUserEqualsAndHashCode() {
        // Test des méthodes equals et hashCode
        User user1 = new User(1L, "helene.leleux@sncf.fr", "Leleux", "Hélène", "password123", "ADMIN", true);
        User user2 = new User(1L, "helene.leleux@sncf.fr", "Leleux", "Hélène", "password123", "ADMIN", true);
        User user3 = new User(2L, "jean.dupont@sncf.fr", "Dupont", "Jean", "password123", "CLIENT", true);

        assertEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertEquals(user1.hashCode(), user2.hashCode());
    }
}