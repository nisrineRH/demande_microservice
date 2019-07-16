package com.demande.open.service;

import com.demande.open.service.dto.HistouriqueStatutDemandeDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing HistouriqueStatutDemande.
 */
public interface HistouriqueStatutDemandeService {

    /**
     * Save a histouriqueStatutDemande.
     *
     * @param histouriqueStatutDemandeDTO the entity to save
     * @return the persisted entity
     */
    HistouriqueStatutDemandeDTO save(HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO);

    /**
     * Get all the histouriqueStatutDemandes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<HistouriqueStatutDemandeDTO> findAll(Pageable pageable);
    /**
     * Get all the HistouriqueStatutDemandeDTO where Demande is null.
     *
     * @return the list of entities
     */
    List<HistouriqueStatutDemandeDTO> findAllWhereDemandeIsNull();


    /**
     * Get the "id" histouriqueStatutDemande.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<HistouriqueStatutDemandeDTO> findOne(Long id);

    /**
     * Delete the "id" histouriqueStatutDemande.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

     Page<HistouriqueStatutDemandeDTO> getHSDByDemande(Long id, Pageable pageable);
}
