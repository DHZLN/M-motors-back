package com.m_motors.mmotors.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nomOriginal;

    @NotBlank
    private String cheminStockage;

    private String typeMime;
    private Long tailleOctets;

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    private LocalDateTime dateUpload;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dossier_id")
    private Dossier dossier;

    @PrePersist
    public void prePersist() {
        dateUpload = LocalDateTime.now();
    }
}