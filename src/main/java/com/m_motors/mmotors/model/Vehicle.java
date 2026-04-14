package com.m_motors.mmotors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "vehicles")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String marque;
    private String modele;
    private Integer annee;
    private Integer kilometrage;
    private String description;

    private Double prixAchat;
    private Double loyerMensuel;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeOffre typeOffre;

    private String photoUrl;

    // Options incluses
    private Boolean assuranceIncluse;
    private Boolean assistanceIncluse;
    private Boolean entretienInclu;
    private Boolean controleTechniqueInclus;
}