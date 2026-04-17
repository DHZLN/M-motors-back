package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class VehicleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VehicleRepository vehicleRepository;

    @Test
    void rechercherVehicules_shouldDisplayVehicleListPage() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .marque("BMW")
                .modele("X1")
                .annee(2022)
                .kilometrage(20000)
                .prixAchat(35000.0)
                .loyerMensuel(450.0)
                .build();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        mockMvc.perform(get("/vehicules"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/liste"))
                .andExpect(model().attributeExists("vehicules"));
    }

    @Test
    void rechercherVehicules_shouldFilterByParams() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(2L)
                .marque("Audi")
                .modele("Q5")
                .prixAchat(50000.0)
                .loyerMensuel(650.0)
                .build();

        when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

        mockMvc.perform(get("/vehicules")
                        .param("marque", "Audi")
                        .param("modele", "Q5")
                        .param("typeOffre", "LLD"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/liste"))
                .andExpect(model().attribute("marque", "Audi"))
                .andExpect(model().attribute("modele", "Q5"))
                .andExpect(model().attribute("typeOffre", TypeOffre.LLD));
    }

    @Test
    void detailVehicule_shouldDisplayDetailPageWhenVehicleExists() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .marque("Mercedes")
                .modele("Classe E")
                .annee(2023)
                .kilometrage(10000)
                .prixAchat(58000.0)
                .description("Berline premium")
                .build();

        when(vehicleRepository.findById(1L)).thenReturn(Optional.of(vehicle));

        mockMvc.perform(get("/vehicules/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/detail"))
                .andExpect(model().attributeExists("vehicule"));
    }

    @Test
    void detailVehicule_shouldRedirectWhenVehicleDoesNotExist() throws Exception {
        when(vehicleRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/vehicules/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicules"));
    }
    @Test
void rechercherVehicules_shouldIgnoreInvalidTypeOffre() throws Exception {
    Vehicle vehicle = Vehicle.builder()
            .id(3L)
            .marque("Audi")
            .modele("A3")
            .prixAchat(30000.0)
            .loyerMensuel(400.0)
            .build();

    when(vehicleRepository.findAll()).thenReturn(List.of(vehicle));

    mockMvc.perform(get("/vehicules")
                    .param("typeOffre", "INVALIDE"))
            .andExpect(status().isOk())
            .andExpect(view().name("vehicules/liste"))
            .andExpect(model().attributeExists("vehicules"));
}

    @Test
    void rechercherVehicules_shouldWorkWithNoFilters() throws Exception {
        when(vehicleRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/vehicules"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/liste"))
                .andExpect(model().attributeExists("vehicules"));
    }

    @Test
    void detailVehicule_shouldRedirectWhenIdDoesNotExist() throws Exception {
        when(vehicleRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/vehicules/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicules"));
    }
    @Test
void rechercherVehicules_shouldFilterByBudgetMax() throws Exception {
    Vehicle vehicle1 = Vehicle.builder()
            .id(1L)
            .marque("Audi")
            .modele("A3")
            .prixAchat(30000.0)
            .loyerMensuel(400.0)
            .build();

    Vehicle vehicle2 = Vehicle.builder()
            .id(2L)
            .marque("BMW")
            .modele("X5")
            .prixAchat(80000.0)
            .loyerMensuel(900.0)
            .build();

    when(vehicleRepository.findAll()).thenReturn(List.of(vehicle1, vehicle2));

    mockMvc.perform(get("/vehicules").param("budgetMax", "50000"))
            .andExpect(status().isOk())
            .andExpect(view().name("vehicules/liste"))
            .andExpect(model().attributeExists("vehicules"))
            .andExpect(model().attribute("budgetMax", 50000.0));
}
}