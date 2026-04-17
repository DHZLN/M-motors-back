package com.m_motors.mmotors.service;

import com.m_motors.mmotors.model.Document;
import com.m_motors.mmotors.repository.DocumentRepository;

public class DocumentService { // Pas @Service pour l'instant
    private final DocumentRepository documentRepository;

    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public Document uploadDocument(Object file, Long dossierId) { // Object file temporaire
        System.out.println("Upload de document pour dossier : " + dossierId);
        return null;
    }
}