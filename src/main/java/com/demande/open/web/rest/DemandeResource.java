package com.demande.open.web.rest;
import com.demande.open.domain.Demande;
import com.demande.open.service.DemandeService;
import com.demande.open.web.rest.errors.BadRequestAlertException;
import com.demande.open.web.rest.util.HeaderUtil;
import com.demande.open.web.rest.util.PaginationUtil;
import com.demande.open.service.dto.DemandeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Demande.
 */
@RestController
@RequestMapping("/api")
public class DemandeResource {

    private final Logger log = LoggerFactory.getLogger(DemandeResource.class);

    private static final String ENTITY_NAME = "demandeMicroServiceDemande";

    private final DemandeService demandeService;

    public DemandeResource(DemandeService demandeService) {
        this.demandeService = demandeService;
    }

    /**
     * POST  /demandes : Create a new demande.
     *
     * @param demandeDTO the demandeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demandeDTO, or with status 400 (Bad Request) if the demande has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demandes")
    public ResponseEntity<DemandeDTO> createDemande(@RequestBody DemandeDTO demandeDTO) throws URISyntaxException {
        log.debug("REST request to save Demande : {}", demandeDTO);
        if (demandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new demande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DemandeDTO result = demandeService.save(demandeDTO);
        return ResponseEntity.created(new URI("/api/demandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demandes : Updates an existing demande.
     *
     * @param demandeDTO the demandeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demandeDTO,
     * or with status 400 (Bad Request) if the demandeDTO is not valid,
     * or with status 500 (Internal Server Error) if the demandeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demandes")
    public ResponseEntity<DemandeDTO> updateDemande(@RequestBody DemandeDTO demandeDTO) throws URISyntaxException {
        log.debug("REST request to update Demande : {}", demandeDTO);
        if (demandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DemandeDTO result = demandeService.save(demandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demandes : get all the demandes.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of demandes in body
     */
    @GetMapping("/demandes")
    public ResponseEntity<List<DemandeDTO>> getAllDemandes(Pageable pageable) {
        log.debug("REST request to get a page of Demandes");
        Page<DemandeDTO> page = demandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demandes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /demandes : get all the demandes.
     *
     * @param String the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of demandes in body
     */
    @GetMapping("/demandes/search")
        public ResponseEntity<List<DemandeDTO>> getAllClient_documentsByFields(@RequestParam String param, Pageable pageable) {
        log.debug("REST request to get a page of Demandes");
        Page<DemandeDTO> page = demandeService.getByFields(param,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demandes/search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /demandes/:id : get the "id" demande.
     *
     * @param id the id of the demandeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demandeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/demandes/{id}")
    public ResponseEntity<DemandeDTO> getDemande(@PathVariable Long id) {
        log.debug("REST request to get Demande : {}", id);
        Optional<DemandeDTO> demandeDTO = demandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demandeDTO);
    }

    /**
     * DELETE  /demandes/:id : delete the "id" demande.
     *
     * @param id the id of the demandeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demandes/{id}")
    public ResponseEntity<Void> deleteDemande(@PathVariable Long id) {
        log.debug("REST request to delete Demande : {}", id);
        demandeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
