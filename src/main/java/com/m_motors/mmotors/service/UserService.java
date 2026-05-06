package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.User;

import java.util.Optional;

public interface UserService {

    User registerUser(String prenom, String nom, String email, String password);

    Optional<User> findByEmail(String email);

    User save(User user);

    boolean emailExists(String email);

    // 🔥 NOUVEAU
    void createPasswordResetToken(String email);

    void resetPassword(String token, String newPassword);
}