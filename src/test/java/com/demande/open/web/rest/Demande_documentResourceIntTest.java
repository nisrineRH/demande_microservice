package com.demande.open.web.rest;

import com.demande.open.DemandeMicroServiceApp;

import com.demande.open.domain.Demande_document;
import com.demande.open.repository.Demande_documentRepository;
import com.demande.open.service.Demande_documentService;
import com.demande.open.service.dto.Demande_documentDTO;
import com.demande.open.service.mapper.Demande_documentMapper;
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
import java.util.List;


import static com.demande.open.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the Demande_documentResource REST controller.
 *
 * @see Demande_documentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemandeMicroServiceApp.class)
public class Demande_documentResourceIntTest {

    private static final String DEFAULT_DM_DOC_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_DM_DOC_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DM_DOC_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DM_DOC_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_URL = "AAAAAAAAAA";
    private static final String UPDATED_URL = "BBBBBBBBBB";

    @Autowired
    private Demande_documentRepository demande_documentRepository;

    @Autowired
    private Demande_documentMapper demande_documentMapper;

    @Autowired
    private Demande_documentService demande_documentService;

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

    private MockMvc restDemande_documentMockMvc;

    private Demande_document demande_document;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Demande_documentResource demande_documentResource = new Demande_documentResource(demande_documentService);
        this.restDemande_documentMockMvc = MockMvcBuilders.standaloneSetup(demande_documentResource)
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
    public static Demande_document createEntity(EntityManager em) {
        Demande_document demande_document = new Demande_document()
            .dm_doc_libelle(DEFAULT_DM_DOC_LIBELLE)
            .dm_doc_type(DEFAULT_DM_DOC_TYPE)
            .url(DEFAULT_URL);
        return demande_document;
    }

    @Before
    public void initTest() {
        demande_document = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemande_document() throws Exception {
        int databaseSizeBeforeCreate = demande_documentRepository.findAll().size();

        // Create the Demande_document
        Demande_documentDTO demande_documentDTO = demande_documentMapper.toDto(demande_document);
        restDemande_documentMockMvc.perform(post("/api/demande-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande_documentDTO)))
            .andExpect(status().isCreated());

        // Validate the Demande_document in the database
        List<Demande_document> demande_documentList = demande_documentRepository.findAll();
        assertThat(demande_documentList).hasSize(databaseSizeBeforeCreate + 1);
        Demande_document testDemande_document = demande_documentList.get(demande_documentList.size() - 1);
        assertThat(testDemande_document.getDm_doc_libelle()).isEqualTo(DEFAULT_DM_DOC_LIBELLE);
        assertThat(testDemande_document.getDm_doc_type()).isEqualTo(DEFAULT_DM_DOC_TYPE);
        assertThat(testDemande_document.getUrl()).isEqualTo(DEFAULT_URL);
    }

    @Test
    @Transactional
    public void createDemande_documentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demande_documentRepository.findAll().size();

        // Create the Demande_document with an existing ID
        demande_document.setId(1L);
        Demande_documentDTO demande_documentDTO = demande_documentMapper.toDto(demande_document);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemande_documentMockMvc.perform(post("/api/demande-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande_document in the database
        List<Demande_document> demande_documentList = demande_documentRepository.findAll();
        assertThat(demande_documentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDemande_documents() throws Exception {
        // Initialize the database
        demande_documentRepository.saveAndFlush(demande_document);

        // Get all the demande_documentList
        restDemande_documentMockMvc.perform(get("/api/demande-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demande_document.getId().intValue())))
            .andExpect(jsonPath("$.[*].dm_doc_libelle").value(hasItem(DEFAULT_DM_DOC_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].dm_doc_type").value(hasItem(DEFAULT_DM_DOC_TYPE.toString())))
            .andExpect(jsonPath("$.[*].url").value(hasItem(DEFAULT_URL.toString())));
    }
    
    @Test
    @Transactional
    public void getDemande_document() throws Exception {
        // Initialize the database
        demande_documentRepository.saveAndFlush(demande_document);

        // Get the demande_document
        restDemande_documentMockMvc.perform(get("/api/demande-documents/{id}", demande_document.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demande_document.getId().intValue()))
            .andExpect(jsonPath("$.dm_doc_libelle").value(DEFAULT_DM_DOC_LIBELLE.toString()))
            .andExpect(jsonPath("$.dm_doc_type").value(DEFAULT_DM_DOC_TYPE.toString()))
            .andExpect(jsonPath("$.url").value(DEFAULT_URL.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemande_document() throws Exception {
        // Get the demande_document
        restDemande_documentMockMvc.perform(get("/api/demande-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemande_document() throws Exception {
        // Initialize the database
        demande_documentRepository.saveAndFlush(demande_document);

        int databaseSizeBeforeUpdate = demande_documentRepository.findAll().size();

        // Update the demande_document
        Demande_document updatedDemande_document = demande_documentRepository.findById(demande_document.getId()).get();
        // Disconnect from session so that the updates on updatedDemande_document are not directly saved in db
        em.detach(updatedDemande_document);
        updatedDemande_document
            .dm_doc_libelle(UPDATED_DM_DOC_LIBELLE)
            .dm_doc_type(UPDATED_DM_DOC_TYPE)
            .url(UPDATED_URL);
        Demande_documentDTO demande_documentDTO = demande_documentMapper.toDto(updatedDemande_document);

        restDemande_documentMockMvc.perform(put("/api/demande-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande_documentDTO)))
            .andExpect(status().isOk());

        // Validate the Demande_document in the database
        List<Demande_document> demande_documentList = demande_documentRepository.findAll();
        assertThat(demande_documentList).hasSize(databaseSizeBeforeUpdate);
        Demande_document testDemande_document = demande_documentList.get(demande_documentList.size() - 1);
        assertThat(testDemande_document.getDm_doc_libelle()).isEqualTo(UPDATED_DM_DOC_LIBELLE);
        assertThat(testDemande_document.getDm_doc_type()).isEqualTo(UPDATED_DM_DOC_TYPE);
        assertThat(testDemande_document.getUrl()).isEqualTo(UPDATED_URL);
    }

    @Test
    @Transactional
    public void updateNonExistingDemande_document() throws Exception {
        int databaseSizeBeforeUpdate = demande_documentRepository.findAll().size();

        // Create the Demande_document
        Demande_documentDTO demande_documentDTO = demande_documentMapper.toDto(demande_document);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemande_documentMockMvc.perform(put("/api/demande-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demande_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande_document in the database
        List<Demande_document> demande_documentList = demande_documentRepository.findAll();
        assertThat(demande_documentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemande_document() throws Exception {
        // Initialize the database
        demande_documentRepository.saveAndFlush(demande_document);

        int databaseSizeBeforeDelete = demande_documentRepository.findAll().size();

        // Delete the demande_document
        restDemande_documentMockMvc.perform(delete("/api/demande-documents/{id}", demande_document.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Demande_document> demande_documentList = demande_documentRepository.findAll();
        assertThat(demande_documentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demande_document.class);
        Demande_document demande_document1 = new Demande_document();
        demande_document1.setId(1L);
        Demande_document demande_document2 = new Demande_document();
        demande_document2.setId(demande_document1.getId());
        assertThat(demande_document1).isEqualTo(demande_document2);
        demande_document2.setId(2L);
        assertThat(demande_document1).isNotEqualTo(demande_document2);
        demande_document1.setId(null);
        assertThat(demande_document1).isNotEqualTo(demande_document2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demande_documentDTO.class);
        Demande_documentDTO demande_documentDTO1 = new Demande_documentDTO();
        demande_documentDTO1.setId(1L);
        Demande_documentDTO demande_documentDTO2 = new Demande_documentDTO();
        assertThat(demande_documentDTO1).isNotEqualTo(demande_documentDTO2);
        demande_documentDTO2.setId(demande_documentDTO1.getId());
        assertThat(demande_documentDTO1).isEqualTo(demande_documentDTO2);
        demande_documentDTO2.setId(2L);
        assertThat(demande_documentDTO1).isNotEqualTo(demande_documentDTO2);
        demande_documentDTO1.setId(null);
        assertThat(demande_documentDTO1).isNotEqualTo(demande_documentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(demande_documentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(demande_documentMapper.fromId(null)).isNull();
    }
}
