package com.m_motors.mmotors.config; // Ou com.m_motors.mmotors.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String redirectUrl = "/"; // URL par défaut si aucun rôle spécifique n'est trouvé

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority grantedAuthority : authorities) {
            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectUrl = "/admin/dashboard"; // Redirige l'admin vers le dashboard admin
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_CLIENT")) {
                redirectUrl = "/client/dashboard"; // Redirige le client vers le dashboard client
                break;
            }
        }

        response.sendRedirect(redirectUrl);
    }
}