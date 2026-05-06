package com.m_motors.mmotors.config;

import com.m_motors.mmotors.model.Role;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.UserRepository;
import com.m_motors.mmotors.repository.VehicleRepository;
import com.m_motors.mmotors.model.TypeOffre;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {

            if (userRepository.findByEmail("admin@mmotors.com").isEmpty()) {
                User admin = new User();
                admin.setEmail("admin@mmotors.com");
                admin.setNom("M-Motors");
                admin.setPrenom("Admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setEnabled(true);
                userRepository.save(admin);
            }

            if (userRepository.findByEmail("client@mmotors.com").isEmpty()) {
                User client = new User();
                client.setEmail("client@mmotors.com");
                client.setNom("Test");
                client.setPrenom("Client");
                client.setPassword(passwordEncoder.encode("test123"));
                client.setRole(Role.CLIENT);
                client.setEnabled(true);
                userRepository.save(client);
            }
        };
    }

@Bean
CommandLineRunner initVehicles(VehicleRepository vehicleRepository) {
    return args -> {

        if (vehicleRepository.count() == 0) {

            Vehicle v1 = new Vehicle();
            v1.setMarque("BMW");
            v1.setModele("Série 5");
            v1.setPrixAchat(72000.0);
            v1.setLoyerMensuel(850.0);
            v1.setTypeOffre(TypeOffre.LLD);
            v1.setImageUrl("/img/bmw.jpg");

            Vehicle v2 = new Vehicle();
            v2.setMarque("Audi");
            v2.setModele("Q5 S-Line");
            v2.setPrixAchat(58000.0);
            v2.setLoyerMensuel(720.0);
            v2.setImageUrl("/img/audi_q5.jpg");

            Vehicle v3 = new Vehicle();
            v3.setMarque("Porsche");
            v3.setModele("Macan GTS");
            v3.setPrixAchat(95000.0);
            v3.setLoyerMensuel(1200.0);
            v3.setTypeOffre(TypeOffre.LLD);
            v3.setImageUrl("/img/porsche.jpg");


            Vehicle v4 = new Vehicle();
            v4.setMarque("Mercedes-Benz");
            v4.setModele("Classe A AMG");
            v4.setPrixAchat(48000.0);
            v4.setLoyerMensuel(650.0);
            v4.setTypeOffre(TypeOffre.LLD);
            v4.setImageUrl("/img/mercedes.jpg");


            Vehicle v5 = new Vehicle();
            v5.setMarque("Tesla");
            v5.setModele("Model 3");
            v5.setPrixAchat(45000.0);
            v5.setLoyerMensuel(580.0);
            v5.setTypeOffre(TypeOffre.LLD);
            v5.setImageUrl("/img/tesla.jpg");

            vehicleRepository.saveAll(List.of(v1, v2, v3, v4, v5));
        }
    };
}}