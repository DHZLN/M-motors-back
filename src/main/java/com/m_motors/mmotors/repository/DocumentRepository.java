package com.m_motors.mmotors.repository;

import com.m_motors.mmotors.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByDossierId(Long dossierId);
}