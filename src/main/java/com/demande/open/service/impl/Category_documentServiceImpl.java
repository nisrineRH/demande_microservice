package com.demande.open.service.impl;

import com.demande.open.service.Category_documentService;
import com.demande.open.domain.Category_document;
import com.demande.open.repository.Category_documentRepository;
import com.demande.open.service.dto.Category_documentDTO;
import com.demande.open.service.mapper.Category_documentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Category_document.
 */
@Service
@Transactional
public class Category_documentServiceImpl implements Category_documentService {

    private final Logger log = LoggerFactory.getLogger(Category_documentServiceImpl.class);

    private final Category_documentRepository category_documentRepository;

    private final Category_documentMapper category_documentMapper;

    public Category_documentServiceImpl(Category_documentRepository category_documentRepository, Category_documentMapper category_documentMapper) {
        this.category_documentRepository = category_documentRepository;
        this.category_documentMapper = category_documentMapper;
    }

    /**
     * Save a category_document.
     *
     * @param category_documentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Category_documentDTO save(Category_documentDTO category_documentDTO) {
        log.debug("Request to save Category_document : {}", category_documentDTO);
        Category_document category_document = category_documentMapper.toEntity(category_documentDTO);
        category_document = category_documentRepository.save(category_document);
        return category_documentMapper.toDto(category_document);
    }

    /**
     * Get all the category_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Category_documentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Category_documents");
        return category_documentRepository.findAll(pageable)
            .map(category_documentMapper::toDto);
    }


    /**
     * Get one category_document by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Category_documentDTO> findOne(Long id) {
        log.debug("Request to get Category_document : {}", id);
        return category_documentRepository.findById(id)
            .map(category_documentMapper::toDto);
    }

    /**
     * Delete the category_document by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category_document : {}", id);
        category_documentRepository.deleteById(id);
    }
}
