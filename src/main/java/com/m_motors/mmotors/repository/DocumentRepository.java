package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.Document;
import java.util.List;
import java.util.Optional;

public interface DocumentRepository {
    Document save(Document document);
    Optional<Document> findById(Long id);
    List<Document> findByDossierId(Long dossierId);
}