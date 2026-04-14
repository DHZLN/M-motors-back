package com.m_motors.mmotors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "dossiers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private User client;

    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicle vehicule;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOffre typeOffre; // ACHAT ou LLD

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutDossier statut; // EN_ATTENTE_DOCUMENTS, EN_COURS_ANALYSE, ACCEPTE, REFUSE

    private LocalDateTime dateCreation;
    private LocalDateTime dateDerniereMiseajour;

    // Options sélectionnées
    private Boolean assuranceTousRisques;
    private Boolean assistanceDepannage;
    private Boolean entretienEtSav;
    private Boolean controleTechniqueInclus;

    @OneToMany(mappedBy = "dossier", cascade = CascadeType.ALL)
    private List<Document> documents;
}