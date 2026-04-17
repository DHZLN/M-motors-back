package com.m_motors.mmotors.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "vehicule_id")
    private Vehicle vehicule;

    @Enumerated(EnumType.STRING)
    private TypeOffre typeOffre;

    @Enumerated(EnumType.STRING)
    private StatutDossier statut;

    private LocalDateTime dateCreation;
}