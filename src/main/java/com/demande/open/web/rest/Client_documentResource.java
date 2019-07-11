package com.demande.open.web.rest;
import com.demande.open.service.Client_documentService;
import com.demande.open.web.rest.errors.BadRequestAlertException;
import com.demande.open.web.rest.util.HeaderUtil;
import com.demande.open.web.rest.util.PaginationUtil;
import com.demande.open.service.dto.Client_documentDTO;
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
 * REST controller for managing Client_document.
 */
@RestController
@RequestMapping("/api")
public class Client_documentResource {

    private final Logger log = LoggerFactory.getLogger(Client_documentResource.class);

    private static final String ENTITY_NAME = "demandeMicroServiceClientDocument";

    private final Client_documentService client_documentService;

    public Client_documentResource(Client_documentService client_documentService) {
        this.client_documentService = client_documentService;
    }

    /**
     * POST  /client-documents : Create a new client_document.
     *
     * @param client_documentDTO the client_documentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new client_documentDTO, or with status 400 (Bad Request) if the client_document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/client-documents")
    public ResponseEntity<Client_documentDTO> createClient_document(@RequestBody Client_documentDTO client_documentDTO) throws URISyntaxException {
        log.debug("REST request to save Client_document : {}", client_documentDTO);
        if (client_documentDTO.getId() != null) {
            throw new BadRequestAlertException("A new client_document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Client_documentDTO result = client_documentService.save(client_documentDTO);
        return ResponseEntity.created(new URI("/api/client-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /client-documents : Updates an existing client_document.
     *
     * @param client_documentDTO the client_documentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated client_documentDTO,
     * or with status 400 (Bad Request) if the client_documentDTO is not valid,
     * or with status 500 (Internal Server Error) if the client_documentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/client-documents")
    public ResponseEntity<Client_documentDTO> updateClient_document(@RequestBody Client_documentDTO client_documentDTO) throws URISyntaxException {
        log.debug("REST request to update Client_document : {}", client_documentDTO);
        if (client_documentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Client_documentDTO result = client_documentService.save(client_documentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, client_documentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /client-documents : get all the client_documents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of client_documents in body
     */
    @GetMapping("/client-documents")
    public ResponseEntity<List<Client_documentDTO>> getAllClient_documents(Pageable pageable) {
        log.debug("REST request to get a page of Client_documents");
        Page<Client_documentDTO> page = client_documentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/client-documents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /client-documents/:id : get the "id" client_document.
     *
     * @param id the id of the client_documentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the client_documentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/client-documents/{id}")
    public ResponseEntity<Client_documentDTO> getClient_document(@PathVariable Long id) {
        log.debug("REST request to get Client_document : {}", id);
        Optional<Client_documentDTO> client_documentDTO = client_documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(client_documentDTO);
    }
    /**
     * GET  /client-documents : get all the client_documents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of client_documents in body
     */
    @GetMapping("/client-documents/search")
    public ResponseEntity<List<Client_documentDTO>> getAllClient_documentsByFields(@RequestParam String param, Pageable pageable) {
        log.debug("REST request to get a page of Client_documents");
        Page<Client_documentDTO> page = client_documentService.getByFields(param,pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/client-documents/search");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }




    /**
     * DELETE  /client-documents/:id : delete the "id" client_document.
     *
     * @param id the id of the client_documentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/client-documents/{id}")
    public ResponseEntity<Void> deleteClient_document(@PathVariable Long id) {
        log.debug("REST request to delete Client_document : {}", id);
        client_documentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
