package com.demande.open.web.rest;
import com.demande.open.service.HistouriqueStatutDemandeService;
import com.demande.open.web.rest.errors.BadRequestAlertException;
import com.demande.open.web.rest.util.HeaderUtil;
import com.demande.open.web.rest.util.PaginationUtil;
import com.demande.open.service.dto.HistouriqueStatutDemandeDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.event.HierarchyBoundsListener;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

/**
 * REST controller for managing HistouriqueStatutDemande.
 */
@RestController
@RequestMapping("/api")
public class HistouriqueStatutDemandeResource {

    private final Logger log = LoggerFactory.getLogger(HistouriqueStatutDemandeResource.class);

    private static final String ENTITY_NAME = "demandeMicroServiceHistouriqueStatutDemande";

    private final HistouriqueStatutDemandeService histouriqueStatutDemandeService;

    public HistouriqueStatutDemandeResource(HistouriqueStatutDemandeService histouriqueStatutDemandeService) {
        this.histouriqueStatutDemandeService = histouriqueStatutDemandeService;
    }

    /**
     * POST  /histourique-statut-demandes : Create a new histouriqueStatutDemande.
     *
     * @param histouriqueStatutDemandeDTO the histouriqueStatutDemandeDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new histouriqueStatutDemandeDTO, or with status 400 (Bad Request) if the histouriqueStatutDemande has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/histourique-statut-demandes")
    public ResponseEntity<HistouriqueStatutDemandeDTO> createHistouriqueStatutDemande(@RequestBody HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO) throws URISyntaxException {
        log.debug("REST request to save HistouriqueStatutDemande : {}", histouriqueStatutDemandeDTO);
        if (histouriqueStatutDemandeDTO.getId() != null) {
            throw new BadRequestAlertException("A new histouriqueStatutDemande cannot already have an ID", ENTITY_NAME, "idexists");
        }
        HistouriqueStatutDemandeDTO result = histouriqueStatutDemandeService.save(histouriqueStatutDemandeDTO);
        return ResponseEntity.created(new URI("/api/histourique-statut-demandes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /histourique-statut-demandes : Updates an existing histouriqueStatutDemande.
     *
     * @param histouriqueStatutDemandeDTO the histouriqueStatutDemandeDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated histouriqueStatutDemandeDTO,
     * or with status 400 (Bad Request) if the histouriqueStatutDemandeDTO is not valid,
     * or with status 500 (Internal Server Error) if the histouriqueStatutDemandeDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/histourique-statut-demandes")
    public ResponseEntity<HistouriqueStatutDemandeDTO> updateHistouriqueStatutDemande(@RequestBody HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO) throws URISyntaxException {
        log.debug("REST request to update HistouriqueStatutDemande : {}", histouriqueStatutDemandeDTO);
        if (histouriqueStatutDemandeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        HistouriqueStatutDemandeDTO result = histouriqueStatutDemandeService.save(histouriqueStatutDemandeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, histouriqueStatutDemandeDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /histourique-statut-demandes : get all the histouriqueStatutDemandes.
     *
     * @param pageable the pagination information
     * @param filter the filter of the request
     * @return the ResponseEntity with status 200 (OK) and the list of histouriqueStatutDemandes in body
     */
    @GetMapping("/histourique-statut-demandes")
    public ResponseEntity<List<HistouriqueStatutDemandeDTO>> getAllHistouriqueStatutDemandes(Pageable pageable, @RequestParam(required = false) String filter) {
        if ("demande-is-null".equals(filter)) {
            log.debug("REST request to get all HistouriqueStatutDemandes where demande is null");
            return new ResponseEntity<>(histouriqueStatutDemandeService.findAllWhereDemandeIsNull(),
                    HttpStatus.OK);
        }
        log.debug("REST request to get a page of HistouriqueStatutDemandes");
        Page<HistouriqueStatutDemandeDTO> page = histouriqueStatutDemandeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/histourique-statut-demandes");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /histourique-statut-demandes/:id : get the "id" histouriqueStatutDemande.
     *
     * @param id the id of the histouriqueStatutDemandeDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the histouriqueStatutDemandeDTO, or with status 404 (Not Found)
     */
    @GetMapping("/histourique-statut-demandes/{id}")
    public ResponseEntity<HistouriqueStatutDemandeDTO> getHistouriqueStatutDemande(@PathVariable Long id) {
        log.debug("REST request to get HistouriqueStatutDemande : {}", id);
        Optional<HistouriqueStatutDemandeDTO> histouriqueStatutDemandeDTO = histouriqueStatutDemandeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(histouriqueStatutDemandeDTO);
    }

    @GetMapping("/histourique-statut-demandes/demande")
    public ResponseEntity<List<HistouriqueStatutDemandeDTO>> getAllHSDByDM(@RequestParam Long param, Pageable pageable) {
        log.debug("REST request to get a page of HistoriqueStatutDemande");
        Page<HistouriqueStatutDemandeDTO> page = histouriqueStatutDemandeService.getHSDByDemande(param, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/histourique-statut-demandes/demande");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }



    /**
     * DELETE  /histourique-statut-demandes/:id : delete the "id" histouriqueStatutDemande.
     *
     * @param id the id of the histouriqueStatutDemandeDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/histourique-statut-demandes/{id}")
    public ResponseEntity<Void> deleteHistouriqueStatutDemande(@PathVariable Long id) {
        log.debug("REST request to delete HistouriqueStatutDemande : {}", id);
        histouriqueStatutDemandeService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
