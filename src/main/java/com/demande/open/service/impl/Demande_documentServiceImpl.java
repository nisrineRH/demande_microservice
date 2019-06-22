package com.demande.open.service.impl;

import com.demande.open.service.Demande_documentService;
import com.demande.open.domain.Demande_document;
import com.demande.open.repository.Demande_documentRepository;
import com.demande.open.service.dto.Demande_documentDTO;
import com.demande.open.service.mapper.Demande_documentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Demande_document.
 */
@Service
@Transactional
public class Demande_documentServiceImpl implements Demande_documentService {

    private final Logger log = LoggerFactory.getLogger(Demande_documentServiceImpl.class);

    private final Demande_documentRepository demande_documentRepository;

    private final Demande_documentMapper demande_documentMapper;

    public Demande_documentServiceImpl(Demande_documentRepository demande_documentRepository, Demande_documentMapper demande_documentMapper) {
        this.demande_documentRepository = demande_documentRepository;
        this.demande_documentMapper = demande_documentMapper;
    }

    /**
     * Save a demande_document.
     *
     * @param demande_documentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Demande_documentDTO save(Demande_documentDTO demande_documentDTO) {
        log.debug("Request to save Demande_document : {}", demande_documentDTO);
        Demande_document demande_document = demande_documentMapper.toEntity(demande_documentDTO);
        demande_document = demande_documentRepository.save(demande_document);
        return demande_documentMapper.toDto(demande_document);
    }

    /**
     * Get all the demande_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Demande_documentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Demande_documents");
        return demande_documentRepository.findAll(pageable)
            .map(demande_documentMapper::toDto);
    }


    /**
     * Get one demande_document by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Demande_documentDTO> findOne(Long id) {
        log.debug("Request to get Demande_document : {}", id);
        return demande_documentRepository.findById(id)
            .map(demande_documentMapper::toDto);
    }

    /**
     * Delete the demande_document by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demande_document : {}", id);
        demande_documentRepository.deleteById(id);
    }
}
