package com.demande.open.service;

import com.demande.open.service.dto.Category_documentDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing Category_document.
 */
public interface Category_documentService {

    /**
     * Save a category_document.
     *
     * @param category_documentDTO the entity to save
     * @return the persisted entity
     */
    Category_documentDTO save(Category_documentDTO category_documentDTO);

    /**
     * Get all the category_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<Category_documentDTO> findAll(Pageable pageable);


    /**
     * Get the "id" category_document.
     *
     * @param id the id of the entity
     * @return the entity
     */
    Optional<Category_documentDTO> findOne(Long id);

    /**
     * Delete the "id" category_document.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
