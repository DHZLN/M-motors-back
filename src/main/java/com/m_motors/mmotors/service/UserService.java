package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.User;
import java.util.Optional;

public interface UserService {
    User registerUser(String prenom, String nom, String email, String password); // La méthode que tu utilises pour l'inscription
    Optional<User> findByEmail(String email);
    User save(User user); // Pour sauvegarder un utilisateur existant ou créé manuellement
    // Ajoutez d'autres méthodes si nécessaire
}