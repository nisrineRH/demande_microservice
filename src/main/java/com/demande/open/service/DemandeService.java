package com.demande.open.service;

import com.demande.open.service.dto.DemandeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Demande.
 */
public interface DemandeService {

    /**
     * Save a demande.
     *
     * @param demandeDTO the entity to save
     * @return the persisted entity
     */
    DemandeDTO save(DemandeDTO demandeDTO);

    /**
     * Get all the demandes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DemandeDTO> findAll(Pageable pageable);


    /**
     * Get the "id" demande.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<DemandeDTO> findOne(Long id);

    /**
     * Delete the "id" demande.
     *
     * @param id the id of the entity
     */
    void delete(Long id);


    Page<DemandeDTO> getByFields(String term, Pageable pageable);


    /**
     * Get demandes By ClientID
     *
     * @param id
     * @param pageable
     * @return
     */
    Page<DemandeDTO> getByClient(Long id, Pageable pageable);
}
