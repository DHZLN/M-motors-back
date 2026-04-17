package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.DossierRepository;
import com.m_motors.mmotors.repository.VehicleRepository;
import com.m_motors.mmotors.service.UserService;
import jakarta.servlet.ServletException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DossierControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DossierRepository dossierRepository;

    @MockBean
    private VehicleRepository vehicleRepository;

    @MockBean
    private UserService userService;

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void mesDossiers_shouldDisplayClientDossiers() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("client@test.com");

        when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(user));
        when(dossierRepository.findByClientId(1L)).thenReturn(List.of());

        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/dossiers"))
                .andExpect(model().attributeExists("dossiers"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void mesDossiers_shouldDisplayEmptyListWhenUserNotFound() throws Exception {
        when(userService.findByEmail("client@test.com")).thenReturn(Optional.empty());

        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/dossiers"))
                .andExpect(model().attributeExists("dossiers"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void nouveauDossier_shouldDisplayForm() throws Exception {
        when(vehicleRepository.findAll()).thenReturn(List.of());

        mockMvc.perform(get("/dossiers/nouveau"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/nouveau-dossier"))
                .andExpect(model().attributeExists("vehicules"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void creerDossier_shouldRedirectAfterCreation() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("client@test.com");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(2L);

        when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(user));
        when(vehicleRepository.findById(2L)).thenReturn(Optional.of(vehicle));

        mockMvc.perform(post("/dossiers").with(csrf())
                        .param("typeOffre", "LLD")
                        .param("vehiculeId", "2"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dossiers?success=created"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void detailDossier_shouldDisplayPageWhenOwnerMatches() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("client@test.com");

        Dossier dossier = new Dossier();
        dossier.setId(10L);
        dossier.setClient(user);

        when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(user));
        when(dossierRepository.findById(10L)).thenReturn(Optional.of(dossier));

        mockMvc.perform(get("/dossiers/10"))
                .andExpect(status().isOk())
                .andExpect(view().name("client/detail-dossier"))
                .andExpect(model().attributeExists("dossier"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void detailDossier_shouldThrowWhenUserIsNotOwner() {
        User connectedUser = new User();
        connectedUser.setId(1L);
        connectedUser.setEmail("client@test.com");

        User otherUser = new User();
        otherUser.setId(2L);

        Dossier dossier = new Dossier();
        dossier.setId(10L);
        dossier.setClient(otherUser);

        when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(connectedUser));
        when(dossierRepository.findById(10L)).thenReturn(Optional.of(dossier));

        ServletException exception = assertThrows(
                ServletException.class,
                () -> mockMvc.perform(get("/dossiers/10"))
        );

        assertTrue(exception.getCause() instanceof RuntimeException);
        assertTrue(exception.getCause().getMessage().contains("Accès non autorisé"));
    }

    @Test
    @WithMockUser(username = "client@test.com", roles = "CLIENT")
    void detailDossier_shouldThrowWhenDossierNotFound() {
        User user = new User();
        user.setId(1L);
        user.setEmail("client@test.com");

        when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(user));
        when(dossierRepository.findById(99L)).thenReturn(Optional.empty());

        ServletException exception = assertThrows(
                ServletException.class,
                () -> mockMvc.perform(get("/dossiers/99"))
        );

        assertTrue(exception.getCause() instanceof RuntimeException);
        assertTrue(exception.getCause().getMessage().contains("Dossier non trouvé"));
    }

    @Test
@WithMockUser(username = "client@test.com", roles = "CLIENT")
void creerDossier_shouldFailWhenUserNotFound() {
    when(userService.findByEmail("client@test.com")).thenReturn(Optional.empty());

    jakarta.servlet.ServletException exception = assertThrows(
            jakarta.servlet.ServletException.class,
            () -> mockMvc.perform(post("/dossiers").with(csrf())
                    .param("typeOffre", "LLD")
                    .param("vehiculeId", "2"))
    );

    assertTrue(exception.getCause() instanceof RuntimeException);
    assertTrue(exception.getCause().getMessage().contains("Utilisateur non trouvé"));
}
@Test
@WithMockUser(username = "client@test.com", roles = "CLIENT")
void creerDossier_shouldFailWhenVehicleNotFound() {
    User user = new User();
    user.setId(1L);
    user.setEmail("client@test.com");

    when(userService.findByEmail("client@test.com")).thenReturn(Optional.of(user));
    when(vehicleRepository.findById(2L)).thenReturn(Optional.empty());

    jakarta.servlet.ServletException exception = assertThrows(
            jakarta.servlet.ServletException.class,
            () -> mockMvc.perform(post("/dossiers").with(csrf())
                    .param("typeOffre", "LLD")
                    .param("vehiculeId", "2"))
    );

    assertTrue(exception.getCause() instanceof RuntimeException);
    assertTrue(exception.getCause().getMessage().contains("Véhicule non trouvé"));
}
}