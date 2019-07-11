package com.demande.open.service.impl;

import com.demande.open.service.DemandeService;
import com.demande.open.domain.Demande;
import com.demande.open.repository.DemandeRepository;
import com.demande.open.service.dto.DemandeDTO;
import com.demande.open.service.mapper.DemandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Optional;

/**
 * Service Implementation for managing Demande.
 */
@Service
@Transactional
public class DemandeServiceImpl implements DemandeService {

    private final Logger log = LoggerFactory.getLogger(DemandeServiceImpl.class);

    private final DemandeRepository demandeRepository;

    private final DemandeMapper demandeMapper;

    public DemandeServiceImpl(DemandeRepository demandeRepository, DemandeMapper demandeMapper) {
        this.demandeRepository = demandeRepository;
        this.demandeMapper = demandeMapper;
    }

    /**
     * Save a demande.
     *
     * @param demandeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public DemandeDTO save(DemandeDTO demandeDTO) {
        log.debug("Request to save Demande : {}", demandeDTO);
        Demande demande = demandeMapper.toEntity(demandeDTO);
        demande = demandeRepository.save(demande);
        return demandeMapper.toDto(demande);
    }

    /**
     * Get all the demandes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DemandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Demandes");
        return demandeRepository.findAll(pageable)
            .map(demandeMapper::toDto);
    }


    /**
     * Get one demande by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DemandeDTO> findOne(Long id) {
        log.debug("Request to get Demande : {}", id);
        return demandeRepository.findById(id)
            .map(demandeMapper::toDto);
    }

    /**
     * Delete the demande by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Demande : {}", id);
        demandeRepository.deleteById(id);
    }

    @Override
    public Page<DemandeDTO> getByFields(String term, Pageable pageable) {
        log.debug(" Request to get all Client_document By Fields");
        return demandeRepository.getByNumLibDM(term, pageable)
            .map(demandeMapper::toDto);

        }
    }



