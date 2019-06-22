package com.demande.open.web.rest;
import com.demande.open.service.Demande_documentService;
import com.demande.open.web.rest.errors.BadRequestAlertException;
import com.demande.open.web.rest.util.HeaderUtil;
import com.demande.open.web.rest.util.PaginationUtil;
import com.demande.open.service.dto.Demande_documentDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Demande_document.
 */
@RestController
@RequestMapping("/api")
public class Demande_documentResource {

    private final Logger log = LoggerFactory.getLogger(Demande_documentResource.class);

    private static final String ENTITY_NAME = "demandeMicroServiceDemandeDocument";

    private final Demande_documentService demande_documentService;

    public Demande_documentResource(Demande_documentService demande_documentService) {
        this.demande_documentService = demande_documentService;
    }

    /**
     * POST  /demande-documents : Create a new demande_document.
     *
     * @param demande_documentDTO the demande_documentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new demande_documentDTO, or with status 400 (Bad Request) if the demande_document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/demande-documents")
    public ResponseEntity<Demande_documentDTO> createDemande_document(@RequestBody Demande_documentDTO demande_documentDTO) throws URISyntaxException {
        log.debug("REST request to save Demande_document : {}", demande_documentDTO);
        if (demande_documentDTO.getId() != null) {
            throw new BadRequestAlertException("A new demande_document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Demande_documentDTO result = demande_documentService.save(demande_documentDTO);
        return ResponseEntity.created(new URI("/api/demande-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /demande-documents : Updates an existing demande_document.
     *
     * @param demande_documentDTO the demande_documentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated demande_documentDTO,
     * or with status 400 (Bad Request) if the demande_documentDTO is not valid,
     * or with status 500 (Internal Server Error) if the demande_documentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/demande-documents")
    public ResponseEntity<Demande_documentDTO> updateDemande_document(@RequestBody Demande_documentDTO demande_documentDTO) throws URISyntaxException {
        log.debug("REST request to update Demande_document : {}", demande_documentDTO);
        if (demande_documentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Demande_documentDTO result = demande_documentService.save(demande_documentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, demande_documentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /demande-documents : get all the demande_documents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of demande_documents in body
     */
    @GetMapping("/demande-documents")
    public ResponseEntity<List<Demande_documentDTO>> getAllDemande_documents(Pageable pageable) {
        log.debug("REST request to get a page of Demande_documents");
        Page<Demande_documentDTO> page = demande_documentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/demande-documents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /demande-documents/:id : get the "id" demande_document.
     *
     * @param id the id of the demande_documentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the demande_documentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/demande-documents/{id}")
    public ResponseEntity<Demande_documentDTO> getDemande_document(@PathVariable Long id) {
        log.debug("REST request to get Demande_document : {}", id);
        Optional<Demande_documentDTO> demande_documentDTO = demande_documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(demande_documentDTO);
    }

    /**
     * DELETE  /demande-documents/:id : delete the "id" demande_document.
     *
     * @param id the id of the demande_documentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/demande-documents/{id}")
    public ResponseEntity<Void> deleteDemande_document(@PathVariable Long id) {
        log.debug("REST request to delete Demande_document : {}", id);
        demande_documentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
