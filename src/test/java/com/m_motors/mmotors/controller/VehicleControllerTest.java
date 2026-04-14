package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.service.VehicleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
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
    private VehicleService vehicleService;

    @Test
    @WithMockUser
    void rechercherVehicules_shouldDisplayVehicleListPage() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .marque("BMW")
                .modele("X1")
                .annee(2022)
                .kilometrage(20000)
                .typeOffre(TypeOffre.LLD)
                .build();

        when(vehicleService.rechercherVehicules(null, null, null))
                .thenReturn(List.of(vehicle));

        mockMvc.perform(get("/vehicules"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/liste"))
                .andExpect(model().attributeExists("vehicules"));
    }

    @Test
    @WithMockUser
    void rechercherVehicules_shouldFilterByParams() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(2L)
                .marque("Audi")
                .modele("Q5")
                .typeOffre(TypeOffre.LLD)
                .build();

        when(vehicleService.rechercherVehicules("Audi", "Q5", TypeOffre.LLD))
                .thenReturn(List.of(vehicle));

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
    @WithMockUser
    void detailVehicule_shouldDisplayDetailPageWhenVehicleExists() throws Exception {
        Vehicle vehicle = Vehicle.builder()
                .id(1L)
                .marque("Mercedes")
                .modele("Classe E")
                .annee(2023)
                .kilometrage(10000)
                .prixAchat(58000.0)
                .typeOffre(TypeOffre.ACHAT)
                .description("Berline premium")
                .build();

        when(vehicleService.getVehiculeById(1L)).thenReturn(Optional.of(vehicle));

        mockMvc.perform(get("/vehicules/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("vehicules/detail"))
                .andExpect(model().attributeExists("vehicule"));
    }

    @Test
    @WithMockUser
    void detailVehicule_shouldRedirectWhenVehicleDoesNotExist() throws Exception {
        when(vehicleService.getVehiculeById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/vehicules/999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/vehicules"));
    }
}