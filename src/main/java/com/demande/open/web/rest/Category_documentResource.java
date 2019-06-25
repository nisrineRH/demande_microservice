package com.demande.open.web.rest;
import com.demande.open.service.Category_documentService;
import com.demande.open.web.rest.errors.BadRequestAlertException;
import com.demande.open.web.rest.util.HeaderUtil;
import com.demande.open.web.rest.util.PaginationUtil;
import com.demande.open.service.dto.Category_documentDTO;
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
 * REST controller for managing Category_document.
 */
@RestController
@RequestMapping("/api")
public class Category_documentResource {

    private final Logger log = LoggerFactory.getLogger(Category_documentResource.class);

    private static final String ENTITY_NAME = "demandeMicroServiceCategoryDocument";

    private final Category_documentService category_documentService;

    public Category_documentResource(Category_documentService category_documentService) {
        this.category_documentService = category_documentService;
    }

    /**
     * POST  /category-documents : Create a new category_document.
     *
     * @param category_documentDTO the category_documentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new category_documentDTO, or with status 400 (Bad Request) if the category_document has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/category-documents")
    public ResponseEntity<Category_documentDTO> createCategory_document(@RequestBody Category_documentDTO category_documentDTO) throws URISyntaxException {
        log.debug("REST request to save Category_document : {}", category_documentDTO);
        if (category_documentDTO.getId() != null) {
            throw new BadRequestAlertException("A new category_document cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Category_documentDTO result = category_documentService.save(category_documentDTO);
        return ResponseEntity.created(new URI("/api/category-documents/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /category-documents : Updates an existing category_document.
     *
     * @param category_documentDTO the category_documentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated category_documentDTO,
     * or with status 400 (Bad Request) if the category_documentDTO is not valid,
     * or with status 500 (Internal Server Error) if the category_documentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/category-documents")
    public ResponseEntity<Category_documentDTO> updateCategory_document(@RequestBody Category_documentDTO category_documentDTO) throws URISyntaxException {
        log.debug("REST request to update Category_document : {}", category_documentDTO);
        if (category_documentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Category_documentDTO result = category_documentService.save(category_documentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, category_documentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /category-documents : get all the category_documents.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of category_documents in body
     */
    @GetMapping("/category-documents")
    public ResponseEntity<List<Category_documentDTO>> getAllCategory_documents(Pageable pageable) {
        log.debug("REST request to get a page of Category_documents");
        Page<Category_documentDTO> page = category_documentService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/category-documents");
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * GET  /category-documents/:id : get the "id" category_document.
     *
     * @param id the id of the category_documentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the category_documentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/category-documents/{id}")
    public ResponseEntity<Category_documentDTO> getCategory_document(@PathVariable Long id) {
        log.debug("REST request to get Category_document : {}", id);
        Optional<Category_documentDTO> category_documentDTO = category_documentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(category_documentDTO);
    }

    /**
     * DELETE  /category-documents/:id : delete the "id" category_document.
     *
     * @param id the id of the category_documentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/category-documents/{id}")
    public ResponseEntity<Void> deleteCategory_document(@PathVariable Long id) {
        log.debug("REST request to delete Category_document : {}", id);
        category_documentService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
