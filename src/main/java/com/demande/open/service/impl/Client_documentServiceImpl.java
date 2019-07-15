package com.demande.open.service.impl;

import com.demande.open.service.Client_documentService;
import com.demande.open.domain.Client_document;
import com.demande.open.repository.Client_documentRepository;
import com.demande.open.service.dto.Client_documentDTO;
import com.demande.open.service.mapper.Client_documentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing Client_document.
 */
@Service
@Transactional
public class Client_documentServiceImpl implements Client_documentService {

    private final Logger log = LoggerFactory.getLogger(Client_documentServiceImpl.class);

    private final Client_documentRepository client_documentRepository;

    private final Client_documentMapper client_documentMapper;

    public Client_documentServiceImpl(Client_documentRepository client_documentRepository, Client_documentMapper client_documentMapper) {
        this.client_documentRepository = client_documentRepository;
        this.client_documentMapper = client_documentMapper;
    }

    /**
     * Save a client_document.
     *
     * @param client_documentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public Client_documentDTO save(Client_documentDTO client_documentDTO) {
        log.debug("Request to save Client_document : {}", client_documentDTO);
        Client_document client_document = client_documentMapper.toEntity(client_documentDTO);
        client_document = client_documentRepository.save(client_document);
        return client_documentMapper.toDto(client_document);
    }

    /**
     * Get all the client_documents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Client_documentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Client_documents");
        return client_documentRepository.findAll(pageable)
            .map(client_documentMapper::toDto);
    }


    /**
     * Get one client_document by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Client_documentDTO> findOne(Long id) {
        log.debug("Request to get Client_document : {}", id);
        return client_documentRepository.findById(id)
            .map(client_documentMapper::toDto);
    }

    /**
     * Delete the client_document by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Client_document : {}", id);
        client_documentRepository.deleteById(id);
    }

    @Override
    public Page<Client_documentDTO> getByFields(String term, Pageable pageable) {
        log.debug(" Request to get all Client_document By Fields");
        return client_documentRepository.getByLibDoc(term, pageable).map(client_documentMapper::toDto);
    }

    @Override
    public Page<Client_documentDTO> getByClient(Long id, Pageable pageable) {
        log.debug(" Request to get all Client_document By ClientID");
        return client_documentRepository.getByClient(id, pageable).map(client_documentMapper::toDto);
    }

    @Override
    public Page<Client_documentDTO> getByCategory(Long id, Pageable pageable) {
        log.debug(" Request to get all Client_document By CategoryID");
        return client_documentRepository.getByCategory(id, pageable).map(client_documentMapper::toDto);
    }

}
