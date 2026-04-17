package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Test
    void login_shouldDisplayLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    void inscription_shouldDisplayRegisterPageWithUserModel() throws Exception {
        mockMvc.perform(get("/inscription"))
                .andExpect(status().isOk())
                .andExpect(view().name("inscription"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void formulaireInscription_shouldDisplayRegisterPageWithUserModel() throws Exception {
        mockMvc.perform(get("/formulaire-inscription"))
                .andExpect(status().isOk())
                .andExpect(view().name("inscription"))
                .andExpect(model().attributeExists("user"));
    }

    @Test
    void register_shouldRedirectWhenPasswordsDoNotMatch() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("prenom", "Jean")
                        .param("nom", "Dupont")
                        .param("email", "jean@test.com")
                        .param("password", "1234")
                        .param("confirmPassword", "9999"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inscription?error=password_mismatch"));
    }

    @Test
    void register_shouldRedirectToLoginWhenSuccess() throws Exception {
        mockMvc.perform(post("/register").with(csrf())
                        .param("prenom", "Jean")
                        .param("nom", "Dupont")
                        .param("email", "jean@test.com")
                        .param("password", "1234")
                        .param("confirmPassword", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/login?success=registered"));
    }
@Test
void clientDashboard_shouldRedirectToLoginWhenAnonymous() throws Exception {
    mockMvc.perform(get("/client/dashboard"))
            .andExpect(status().is3xxRedirection());
}

@Test
void registerPage_shouldBeAccessibleFromRegisterUrl() throws Exception {
    mockMvc.perform(get("/register"))
            .andExpect(status().isOk())
            .andExpect(view().name("inscription"))
            .andExpect(model().attributeExists("user"));
}
    @Test
    void register_shouldRedirectWhenEmailAlreadyExists() throws Exception {
        doThrow(new RuntimeException("email exists"))
                .when(userService)
                .registerUser(anyString(), anyString(), anyString(), anyString());

        mockMvc.perform(post("/register").with(csrf())
                        .param("prenom", "Jean")
                        .param("nom", "Dupont")
                        .param("email", "jean@test.com")
                        .param("password", "1234")
                        .param("confirmPassword", "1234"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/inscription?error=email_exists"));
    }
@Test
void clientDashboard_shouldBeAccessibleWhenAuthenticated() throws Exception {
    mockMvc.perform(get("/client/dashboard")
                    .with(org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user("client@test.com").roles("CLIENT")))
            .andExpect(status().isOk())
            .andExpect(view().name("client/dashboard"))
            .andExpect(model().attributeExists("title"));
}
}