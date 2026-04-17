package com.m_motors.mmotors.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class VehicleTest {

    @Test
    public void testVehicleGettersAndSetters() {
        // Création d'un véhicule
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1L);
        vehicle.setMarque("Renault");
        vehicle.setModele("Kangoo");
        vehicle.setAnnee(2023);
        vehicle.setImmatriculation("AB-123-CD");
        vehicle.setKilometrage(15000);

        // Vérification des getters
        assertEquals(1L, vehicle.getId());
        assertEquals("Renault", vehicle.getMarque());
        assertEquals("Kangoo", vehicle.getModele());
        assertEquals(2023, vehicle.getAnnee());
        assertEquals("AB-123-CD", vehicle.getImmatriculation());
        assertEquals(15000, vehicle.getKilometrage());
    }

    @Test
    public void testVehicleDefaultConstructor() {
        // Test du constructeur par défaut
        Vehicle vehicle = new Vehicle();
        assertNull(vehicle.getId());
        assertNull(vehicle.getMarque());
        assertNull(vehicle.getModele());
        assertEquals(0, vehicle.getAnnee());
        assertNull(vehicle.getImmatriculation());
        assertEquals(0, vehicle.getKilometrage());
    }

    @Test
    public void testVehicleAllArgsConstructor() {
        // Test du constructeur avec tous les arguments
        Vehicle vehicle = new Vehicle(2L, "Peugeot", "Partner", 2022, "CD-456-EF", 25000);
        assertEquals(2L, vehicle.getId());
        assertEquals("Peugeot", vehicle.getMarque());
        assertEquals("Partner", vehicle.getModele());
        assertEquals(2022, vehicle.getAnnee());
        assertEquals("CD-456-EF", vehicle.getImmatriculation());
        assertEquals(25000, vehicle.getKilometrage());
    }

    @Test
    public void testVehicleEqualsAndHashCode() {
        // Test des méthodes equals et hashCode
        Vehicle vehicle1 = new Vehicle(1L, "Renault", "Kangoo", 2023, "AB-123-CD", 15000);
        Vehicle vehicle2 = new Vehicle(1L, "Renault", "Kangoo", 2023, "AB-123-CD", 15000);
        Vehicle vehicle3 = new Vehicle(2L, "Peugeot", "Partner", 2022, "CD-456-EF", 25000);

        assertEquals(vehicle1, vehicle2);
        assertNotEquals(vehicle1, vehicle3);
        assertEquals(vehicle1.hashCode(), vehicle2.hashCode());
    }
}