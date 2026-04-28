package com.m_motors.mmotors.data;

import com.m_motors.mmotors.model.Role;
import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.UserRepository;
import com.m_motors.mmotors.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {

    private final VehicleRepository vehicleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(VehicleRepository vehicleRepository,
                      UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        loadUserData();

        if (vehicleRepository.count() == 0) {
            loadVehicleData();
        }
    }

    private void loadUserData() {
        if (userRepository.findByEmail("admin@mmotors.com").isEmpty()) {
            User admin = new User();
            admin.setPrenom("Admin");
            admin.setNom("M-Motors");
            admin.setEmail("admin@mmotors.com");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            admin.setEnabled(true);
            userRepository.save(admin);
            System.out.println("Utilisateur admin créé avec succès.");
        }

        if (userRepository.findByEmail("client@mmotors.com").isEmpty()) {
            User client = new User();
            client.setPrenom("Client");
            client.setNom("Test");
            client.setEmail("client@mmotors.com");
            client.setPassword(passwordEncoder.encode("client123"));
            client.setRole(Role.CLIENT);
            client.setEnabled(true);
            userRepository.save(client);
            System.out.println("Utilisateur client créé avec succès.");
        }
    }

    private void loadVehicleData() {
        List<Vehicle> vehicles = List.of(
            Vehicle.builder()
                .marque("BMW")
                .modele("Série 5")
                .annee(2023)
                .kilometrage(15000)
                .description("Berline de luxe alliant confort et performance, idéale pour les longs trajets et les déplacements professionnels.")
                .prixAchat(72000.0)
                .loyerMensuel(850.0)
                .typeOffre(TypeOffre.ACHAT)
                .photoUrl("/img/bmw.jpg")
                .build(),
            Vehicle.builder()
                .marque("Audi")
                .modele("Q5 S-Line")
                .annee(2022)
                .kilometrage(25000)
                .description("SUV polyvalent et élégant, parfait pour la famille avec ses technologies de pointe et son intérieur spacieux.")
                .prixAchat(58000.0)
                .loyerMensuel(720.0)
                .typeOffre(TypeOffre.LLD)
                .photoUrl("/img/audiq5.jpg")
                .build(),
            Vehicle.builder()
                .marque("Porsche")
                .modele("Macan GTS")
                .annee(2023)
                .kilometrage(8000)
                .description("L'expérience de conduite sportive Porsche dans un format SUV compact. Puissance et agilité garanties.")
                .prixAchat(95000.0)
                .loyerMensuel(1200.0)
                .typeOffre(TypeOffre.LLD)
                .photoUrl("/img/porsche.jpg")
                .build(),
            Vehicle.builder()
                .marque("Mercedes-Benz")
                .modele("Classe A AMG")
                .annee(2022)
                .kilometrage(19000)
                .description("Compacte sportive au design affirmé. Profitez du savoir-faire AMG au quotidien.")
                .prixAchat(48000.0)
                .loyerMensuel(650.0)
                .typeOffre(TypeOffre.ACHAT)
                .photoUrl("https://images.pexels.com/photos/112460/pexels-photo-112460.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1")
                .build(),
            Vehicle.builder()
                .marque("Tesla")
                .modele("Model 3")
                .annee(2023)
                .kilometrage(12000)
                .description("La référence des berlines électriques. Autonomie, performance et technologie minimaliste.")
                .prixAchat(45000.0)
                .loyerMensuel(580.0)
                .typeOffre(TypeOffre.LLD)
               .photoUrl("/img/tesla.jpg")
                .build()
        );

        vehicleRepository.saveAll(vehicles);
    }
}