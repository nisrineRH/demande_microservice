package com.demande.open.service;

import com.demande.open.domain.Demande_document;
import com.demande.open.service.dto.Demande_documentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Demande_document.
 */
public interface Demande_documentService {

    /**
     * Save a demande_document.
     *
     * @param demande_documentDTO the entity to save
     * @return the persisted entity
     */
    Demande_documentDTO save(Demande_documentDTO demande_documentDTO);

    /**
     * Get all the demande_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Demande_documentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" demande_document.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Demande_documentDTO> findOne(Long id);

    /**
     * Delete the "id" demande_document.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<Demande_documentDTO> getByDemande(Long id, Pageable pageable);
}
