package com.demande.open.service.impl;

import com.demande.open.service.HistouriqueStatutDemandeService;
import com.demande.open.domain.HistouriqueStatutDemande;
import com.demande.open.repository.HistouriqueStatutDemandeRepository;
import com.demande.open.service.dto.HistouriqueStatutDemandeDTO;
import com.demande.open.service.mapper.HistouriqueStatutDemandeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Service Implementation for managing HistouriqueStatutDemande.
 */
@Service
@Transactional
public class HistouriqueStatutDemandeServiceImpl implements HistouriqueStatutDemandeService {

    private final Logger log = LoggerFactory.getLogger(HistouriqueStatutDemandeServiceImpl.class);

    private final HistouriqueStatutDemandeRepository histouriqueStatutDemandeRepository;

    private final HistouriqueStatutDemandeMapper histouriqueStatutDemandeMapper;

    public HistouriqueStatutDemandeServiceImpl(HistouriqueStatutDemandeRepository histouriqueStatutDemandeRepository, HistouriqueStatutDemandeMapper histouriqueStatutDemandeMapper) {
        this.histouriqueStatutDemandeRepository = histouriqueStatutDemandeRepository;
        this.histouriqueStatutDemandeMapper = histouriqueStatutDemandeMapper;
    }

    /**
     * Save a histouriqueStatutDemande.
     *
     * @param histouriqueStatutDemandeDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public HistouriqueStatutDemandeDTO save(HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO) {
        log.debug("Request to save HistouriqueStatutDemande : {}", histouriqueStatutDemandeDTO);
        HistouriqueStatutDemande histouriqueStatutDemande = histouriqueStatutDemandeMapper.toEntity(histouriqueStatutDemandeDTO);
        histouriqueStatutDemande = histouriqueStatutDemandeRepository.save(histouriqueStatutDemande);
        return histouriqueStatutDemandeMapper.toDto(histouriqueStatutDemande);
    }

    /**
     * Get all the histouriqueStatutDemandes.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<HistouriqueStatutDemandeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all HistouriqueStatutDemandes");
        return histouriqueStatutDemandeRepository.findAll(pageable)
            .map(histouriqueStatutDemandeMapper::toDto);
    }



    /**
     *  get all the histouriqueStatutDemandes where Demande is null.
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public List<HistouriqueStatutDemandeDTO> findAllWhereDemandeIsNull() {
        log.debug("Request to get all histouriqueStatutDemandes where Demande is null");
        return StreamSupport
            .stream(histouriqueStatutDemandeRepository.findAll().spliterator(), false)
            .filter(histouriqueStatutDemande -> histouriqueStatutDemande.getDemande() == null)
            .map(histouriqueStatutDemandeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one histouriqueStatutDemande by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<HistouriqueStatutDemandeDTO> findOne(Long id) {
        log.debug("Request to get HistouriqueStatutDemande : {}", id);
        return histouriqueStatutDemandeRepository.findById(id)
            .map(histouriqueStatutDemandeMapper::toDto);
    }

    /**
     * Delete the histouriqueStatutDemande by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete HistouriqueStatutDemande : {}", id);
        histouriqueStatutDemandeRepository.deleteById(id);
    }
}
