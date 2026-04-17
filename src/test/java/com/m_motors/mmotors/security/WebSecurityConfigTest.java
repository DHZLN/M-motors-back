package com.m_motors.mmotors.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk());
    }

    @Test
    void loginPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk());
    }

    @Test
    void registerPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/formulaire-inscription"))
                .andExpect(status().isOk());
    }

    @Test
    void vehiculesPage_shouldBeAccessibleWithoutAuthentication() throws Exception {
        mockMvc.perform(get("/vehicules"))
                .andExpect(status().isOk());
    }

    @Test
    void staticResources_shouldNotRedirectToLogin() throws Exception {
        mockMvc.perform(get("/css/style.css"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/js/app.js"))
                .andExpect(status().isNotFound());

        mockMvc.perform(get("/images/logo.png"))
                .andExpect(status().isNotFound());
    }

    @Test
    void protectedEndpoint_shouldRedirectToLoginWhenNotAuthenticated() throws Exception {
        mockMvc.perform(get("/some-protected-page"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrlPattern("**/login"));
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void adminEndpoint_shouldBeForbiddenForClientRole() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void adminEndpoint_shouldNotBeForbiddenForAdminRole() throws Exception {
        mockMvc.perform(get("/admin/dashboard"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "CLIENT")
    void clientEndpoint_shouldNotBeForbiddenForClientRole() throws Exception {
        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    void clientEndpoint_shouldBeForbiddenForAdminRole() throws Exception {
        mockMvc.perform(get("/dossiers"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser
    void logout_shouldRedirectToHomePage() throws Exception {
        mockMvc.perform(post("/logout").with(csrf()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/"));
    }

    @Test
    void passwordEncoder_shouldBeConfigured() {
        org.springframework.security.crypto.password.PasswordEncoder encoder =
                new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();

        String rawPassword = "test123";
        String encodedPassword = encoder.encode(rawPassword);

        assert encoder.matches(rawPassword, encodedPassword);
        assert !encoder.matches("wrongPassword", encodedPassword);
    }
}