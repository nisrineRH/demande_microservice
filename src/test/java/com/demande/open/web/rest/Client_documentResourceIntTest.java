package com.demande.open.web.rest;

import com.demande.open.DemandeMicroServiceApp;

import com.demande.open.domain.Client_document;
import com.demande.open.repository.Client_documentRepository;
import com.demande.open.service.Client_documentService;
import com.demande.open.service.dto.Client_documentDTO;
import com.demande.open.service.mapper.Client_documentMapper;
import com.demande.open.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;


import static com.demande.open.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Client_documentResource REST controller.
 *
 * @see Client_documentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemandeMicroServiceApp.class)
public class Client_documentResourceIntTest {

    private static final String DEFAULT_DOC_CHEMIN = "AAAAAAAAAA";
    private static final String UPDATED_DOC_CHEMIN = "BBBBBBBBBB";

    private static final String DEFAULT_DOC_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_DOC_LIBELLE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DOC_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DOC_DATE = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private Client_documentRepository client_documentRepository;

    @Autowired
    private Client_documentMapper client_documentMapper;

    @Autowired
    private Client_documentService client_documentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restClient_documentMockMvc;

    private Client_document client_document;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Client_documentResource client_documentResource = new Client_documentResource(client_documentService);
        this.restClient_documentMockMvc = MockMvcBuilders.standaloneSetup(client_documentResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Client_document createEntity(EntityManager em) {
        Client_document client_document = new Client_document()
            .doc_chemin(DEFAULT_DOC_CHEMIN)
            .doc_libelle(DEFAULT_DOC_LIBELLE)
            .doc_date(DEFAULT_DOC_DATE);
        return client_document;
    }

    @Before
    public void initTest() {
        client_document = createEntity(em);
    }

    @Test
    @Transactional
    public void createClient_document() throws Exception {
        int databaseSizeBeforeCreate = client_documentRepository.findAll().size();

        // Create the Client_document
        Client_documentDTO client_documentDTO = client_documentMapper.toDto(client_document);
        restClient_documentMockMvc.perform(post("/api/client-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(client_documentDTO)))
            .andExpect(status().isCreated());

        // Validate the Client_document in the database
        List<Client_document> client_documentList = client_documentRepository.findAll();
        assertThat(client_documentList).hasSize(databaseSizeBeforeCreate + 1);
        Client_document testClient_document = client_documentList.get(client_documentList.size() - 1);
        assertThat(testClient_document.getDoc_chemin()).isEqualTo(DEFAULT_DOC_CHEMIN);
        assertThat(testClient_document.getDoc_libelle()).isEqualTo(DEFAULT_DOC_LIBELLE);
        assertThat(testClient_document.getDoc_date()).isEqualTo(DEFAULT_DOC_DATE);
    }

    @Test
    @Transactional
    public void createClient_documentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = client_documentRepository.findAll().size();

        // Create the Client_document with an existing ID
        client_document.setId(1L);
        Client_documentDTO client_documentDTO = client_documentMapper.toDto(client_document);

        // An entity with an existing ID cannot be created, so this API call must fail
        restClient_documentMockMvc.perform(post("/api/client-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(client_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client_document in the database
        List<Client_document> client_documentList = client_documentRepository.findAll();
        assertThat(client_documentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllClient_documents() throws Exception {
        // Initialize the database
        client_documentRepository.saveAndFlush(client_document);

        // Get all the client_documentList
        restClient_documentMockMvc.perform(get("/api/client-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(client_document.getId().intValue())))
            .andExpect(jsonPath("$.[*].doc_chemin").value(hasItem(DEFAULT_DOC_CHEMIN.toString())))
            .andExpect(jsonPath("$.[*].doc_libelle").value(hasItem(DEFAULT_DOC_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].doc_date").value(hasItem(DEFAULT_DOC_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getClient_document() throws Exception {
        // Initialize the database
        client_documentRepository.saveAndFlush(client_document);

        // Get the client_document
        restClient_documentMockMvc.perform(get("/api/client-documents/{id}", client_document.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(client_document.getId().intValue()))
            .andExpect(jsonPath("$.doc_chemin").value(DEFAULT_DOC_CHEMIN.toString()))
            .andExpect(jsonPath("$.doc_libelle").value(DEFAULT_DOC_LIBELLE.toString()))
            .andExpect(jsonPath("$.doc_date").value(DEFAULT_DOC_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingClient_document() throws Exception {
        // Get the client_document
        restClient_documentMockMvc.perform(get("/api/client-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateClient_document() throws Exception {
        // Initialize the database
        client_documentRepository.saveAndFlush(client_document);

        int databaseSizeBeforeUpdate = client_documentRepository.findAll().size();

        // Update the client_document
        Client_document updatedClient_document = client_documentRepository.findById(client_document.getId()).get();
        // Disconnect from session so that the updates on updatedClient_document are not directly saved in db
        em.detach(updatedClient_document);
        updatedClient_document
            .doc_chemin(UPDATED_DOC_CHEMIN)
            .doc_libelle(UPDATED_DOC_LIBELLE)
            .doc_date(UPDATED_DOC_DATE);
        Client_documentDTO client_documentDTO = client_documentMapper.toDto(updatedClient_document);

        restClient_documentMockMvc.perform(put("/api/client-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(client_documentDTO)))
            .andExpect(status().isOk());

        // Validate the Client_document in the database
        List<Client_document> client_documentList = client_documentRepository.findAll();
        assertThat(client_documentList).hasSize(databaseSizeBeforeUpdate);
        Client_document testClient_document = client_documentList.get(client_documentList.size() - 1);
        assertThat(testClient_document.getDoc_chemin()).isEqualTo(UPDATED_DOC_CHEMIN);
        assertThat(testClient_document.getDoc_libelle()).isEqualTo(UPDATED_DOC_LIBELLE);
        assertThat(testClient_document.getDoc_date()).isEqualTo(UPDATED_DOC_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingClient_document() throws Exception {
        int databaseSizeBeforeUpdate = client_documentRepository.findAll().size();

        // Create the Client_document
        Client_documentDTO client_documentDTO = client_documentMapper.toDto(client_document);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restClient_documentMockMvc.perform(put("/api/client-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(client_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Client_document in the database
        List<Client_document> client_documentList = client_documentRepository.findAll();
        assertThat(client_documentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteClient_document() throws Exception {
        // Initialize the database
        client_documentRepository.saveAndFlush(client_document);

        int databaseSizeBeforeDelete = client_documentRepository.findAll().size();

        // Delete the client_document
        restClient_documentMockMvc.perform(delete("/api/client-documents/{id}", client_document.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Client_document> client_documentList = client_documentRepository.findAll();
        assertThat(client_documentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Client_document.class);
        Client_document client_document1 = new Client_document();
        client_document1.setId(1L);
        Client_document client_document2 = new Client_document();
        client_document2.setId(client_document1.getId());
        assertThat(client_document1).isEqualTo(client_document2);
        client_document2.setId(2L);
        assertThat(client_document1).isNotEqualTo(client_document2);
        client_document1.setId(null);
        assertThat(client_document1).isNotEqualTo(client_document2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Client_documentDTO.class);
        Client_documentDTO client_documentDTO1 = new Client_documentDTO();
        client_documentDTO1.setId(1L);
        Client_documentDTO client_documentDTO2 = new Client_documentDTO();
        assertThat(client_documentDTO1).isNotEqualTo(client_documentDTO2);
        client_documentDTO2.setId(client_documentDTO1.getId());
        assertThat(client_documentDTO1).isEqualTo(client_documentDTO2);
        client_documentDTO2.setId(2L);
        assertThat(client_documentDTO1).isNotEqualTo(client_documentDTO2);
        client_documentDTO1.setId(null);
        assertThat(client_documentDTO1).isNotEqualTo(client_documentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(client_documentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(client_documentMapper.fromId(null)).isNull();
    }
}
