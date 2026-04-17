package com.m_motors.mmotors.controller;

import com.m_motors.mmotors.model.Dossier;
import com.m_motors.mmotors.model.StatutDossier;
import com.m_motors.mmotors.model.TypeOffre;
import com.m_motors.mmotors.model.User;
import com.m_motors.mmotors.model.Vehicle;
import com.m_motors.mmotors.repository.DossierRepository;
import com.m_motors.mmotors.repository.VehicleRepository;
import com.m_motors.mmotors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@PreAuthorize("hasRole('CLIENT')")
public class DossierController {

    @Autowired
    private DossierRepository dossierRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserService userService;

 @GetMapping("/dossiers")
public String mesDossiers(@AuthenticationPrincipal UserDetails userDetails, Model model) {
    String email = userDetails.getUsername();

    var userOpt = userService.findByEmail(email);

    if (userOpt.isEmpty()) {
        model.addAttribute("dossiers", List.of());
        model.addAttribute("title", "Mes Dossiers - M-Motors");
        return "client/dossiers";
    }

    User user = userOpt.get();
    List<Dossier> dossiers = dossierRepository.findByClientId(user.getId());

    model.addAttribute("dossiers", dossiers);
    model.addAttribute("title", "Mes Dossiers - M-Motors");
    return "client/dossiers";
}

    @GetMapping("/dossiers/nouveau")
    public String nouveauDossier(Model model) {
        List<Vehicle> vehicules = vehicleRepository.findAll();

        model.addAttribute("vehicules", vehicules);
        model.addAttribute("title", "Nouveau Dossier - M-Motors");
        return "client/nouveau-dossier";
    }

    @PostMapping("/dossiers")
    public String creerDossier(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam("typeOffre") String typeOffre,
            @RequestParam("vehiculeId") Long vehiculeId,
            @RequestParam(value = "budget", required = false) Double budget,
            @RequestParam(value = "commentaire", required = false) String commentaire) {

        String email = userDetails.getUsername();

        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email));

        Vehicle vehicle = vehicleRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Véhicule non trouvé"));

        Dossier dossier = new Dossier();
        dossier.setClient(user);
        dossier.setVehicule(vehicle);
        dossier.setTypeOffre(TypeOffre.valueOf(typeOffre));
        dossier.setStatut(StatutDossier.EN_ATTENTE_DOCUMENTS);
        dossier.setDateCreation(LocalDateTime.now());

        dossierRepository.save(dossier);

        return "redirect:/dossiers?success=created";
    }

    @GetMapping("/dossiers/{id}")
    public String detailDossier(
            @PathVariable Long id,
            @AuthenticationPrincipal UserDetails userDetails,
            Model model) {

        String email = userDetails.getUsername();

        User user = userService.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'email : " + email));

        Dossier dossier = dossierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dossier non trouvé"));

        if (!dossier.getClient().getId().equals(user.getId())) {
            throw new RuntimeException("Accès non autorisé à ce dossier");
        }

        model.addAttribute("dossier", dossier);
        model.addAttribute("title", "Dossier #" + id + " - M-Motors");
        return "client/detail-dossier";
    }
}