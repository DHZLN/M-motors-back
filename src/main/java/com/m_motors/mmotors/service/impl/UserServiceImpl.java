package com.m_motors.mmotors.service.impl;

import com.m_motors.mmotors.model.PasswordResetToken;
import com.m_motors.mmotors.model.Role;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.repository.PasswordResetTokenRepository;
import com.m_motors.mmotors.repository.UserRepository;
import com.m_motors.mmotors.service.EmailService;
import com.m_motors.mmotors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final PasswordResetTokenRepository tokenRepository;
    private final EmailService emailService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           PasswordResetTokenRepository tokenRepository,
                           EmailService emailService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    // =========================
    // INSCRIPTION
    // =========================
    @Override
    public User registerUser(String prenom, String nom, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Un utilisateur avec cet email existe déjà !");
        }

        User newUser = new User();
        newUser.setPrenom(prenom);
        newUser.setNom(nom);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(Role.CLIENT);
        newUser.setEnabled(true);

        return userRepository.save(newUser);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public boolean emailExists(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    // =========================
    // RESET PASSWORD
    // =========================

    @Override
    public void createPasswordResetToken(String email) {

        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) return;

        User user = userOpt.get();

        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = new PasswordResetToken();
        resetToken.setToken(token);
        resetToken.setUser(user);
        resetToken.setExpirationDate(LocalDateTime.now().plusHours(1));

        tokenRepository.save(resetToken);

        String link = "http://localhost:8080/reset-password?token=" + token;

        emailService.send(email, "Réinitialisation mot de passe", link);
    }

    @Override
    public void resetPassword(String token, String newPassword) {

        PasswordResetToken resetToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Token invalide"));

        if (resetToken.getExpirationDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expiré");
        }

        User user = resetToken.getUser();
        user.setPassword(passwordEncoder.encode(newPassword));

        userRepository.save(user);
    }
}