package com.demande.open.service;

import com.demande.open.service.dto.Client_documentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Client_document.
 */
public interface Client_documentService {

    /**
     * Save a client_document.
     *
     * @param client_documentDTO the entity to save
     * @return the persisted entity
     */
    Client_documentDTO save(Client_documentDTO client_documentDTO);

    /**
     * Get all the client_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Client_documentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" client_document.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Client_documentDTO> findOne(Long id);

    /**
     * Delete the "id" client_document.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    Page<Client_documentDTO> getByFields(String term, Pageable pageable);
}
