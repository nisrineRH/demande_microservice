package com.demande.open.web.rest;

import com.demande.open.DemandeMicroServiceApp;

import com.demande.open.domain.Category_document;
import com.demande.open.repository.Category_documentRepository;
import com.demande.open.service.Category_documentService;
import com.demande.open.service.dto.Category_documentDTO;
import com.demande.open.service.mapper.Category_documentMapper;
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
 * Test class for the Category_documentResource REST controller.
 *
 * @see Category_documentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemandeMicroServiceApp.class)
public class Category_documentResourceIntTest {

    private static final Integer DEFAULT_CAT_NUMERO = 1;
    private static final Integer UPDATED_CAT_NUMERO = 2;

    private static final String DEFAULT_CAT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_CAT_NOM = "BBBBBBBBBB";

    @Autowired
    private Category_documentRepository category_documentRepository;

    @Autowired
    private Category_documentMapper category_documentMapper;

    @Autowired
    private Category_documentService category_documentService;

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

    private MockMvc restCategory_documentMockMvc;

    private Category_document category_document;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final Category_documentResource category_documentResource = new Category_documentResource(category_documentService);
        this.restCategory_documentMockMvc = MockMvcBuilders.standaloneSetup(category_documentResource)
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
    public static Category_document createEntity(EntityManager em) {
        Category_document category_document = new Category_document()
            .cat_numero(DEFAULT_CAT_NUMERO)
            .cat_nom(DEFAULT_CAT_NOM);
        return category_document;
    }

    @Before
    public void initTest() {
        category_document = createEntity(em);
    }

    @Test
    @Transactional
    public void createCategory_document() throws Exception {
        int databaseSizeBeforeCreate = category_documentRepository.findAll().size();

        // Create the Category_document
        Category_documentDTO category_documentDTO = category_documentMapper.toDto(category_document);
        restCategory_documentMockMvc.perform(post("/api/category-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category_documentDTO)))
            .andExpect(status().isCreated());

        // Validate the Category_document in the database
        List<Category_document> category_documentList = category_documentRepository.findAll();
        assertThat(category_documentList).hasSize(databaseSizeBeforeCreate + 1);
        Category_document testCategory_document = category_documentList.get(category_documentList.size() - 1);
        assertThat(testCategory_document.getCat_numero()).isEqualTo(DEFAULT_CAT_NUMERO);
        assertThat(testCategory_document.getCat_nom()).isEqualTo(DEFAULT_CAT_NOM);
    }

    @Test
    @Transactional
    public void createCategory_documentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = category_documentRepository.findAll().size();

        // Create the Category_document with an existing ID
        category_document.setId(1L);
        Category_documentDTO category_documentDTO = category_documentMapper.toDto(category_document);

        // An entity with an existing ID cannot be created, so this API call must fail
        restCategory_documentMockMvc.perform(post("/api/category-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category_document in the database
        List<Category_document> category_documentList = category_documentRepository.findAll();
        assertThat(category_documentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllCategory_documents() throws Exception {
        // Initialize the database
        category_documentRepository.saveAndFlush(category_document);

        // Get all the category_documentList
        restCategory_documentMockMvc.perform(get("/api/category-documents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(category_document.getId().intValue())))
            .andExpect(jsonPath("$.[*].cat_numero").value(hasItem(DEFAULT_CAT_NUMERO)))
            .andExpect(jsonPath("$.[*].cat_nom").value(hasItem(DEFAULT_CAT_NOM.toString())));
    }
    
    @Test
    @Transactional
    public void getCategory_document() throws Exception {
        // Initialize the database
        category_documentRepository.saveAndFlush(category_document);

        // Get the category_document
        restCategory_documentMockMvc.perform(get("/api/category-documents/{id}", category_document.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(category_document.getId().intValue()))
            .andExpect(jsonPath("$.cat_numero").value(DEFAULT_CAT_NUMERO))
            .andExpect(jsonPath("$.cat_nom").value(DEFAULT_CAT_NOM.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingCategory_document() throws Exception {
        // Get the category_document
        restCategory_documentMockMvc.perform(get("/api/category-documents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateCategory_document() throws Exception {
        // Initialize the database
        category_documentRepository.saveAndFlush(category_document);

        int databaseSizeBeforeUpdate = category_documentRepository.findAll().size();

        // Update the category_document
        Category_document updatedCategory_document = category_documentRepository.findById(category_document.getId()).get();
        // Disconnect from session so that the updates on updatedCategory_document are not directly saved in db
        em.detach(updatedCategory_document);
        updatedCategory_document
            .cat_numero(UPDATED_CAT_NUMERO)
            .cat_nom(UPDATED_CAT_NOM);
        Category_documentDTO category_documentDTO = category_documentMapper.toDto(updatedCategory_document);

        restCategory_documentMockMvc.perform(put("/api/category-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category_documentDTO)))
            .andExpect(status().isOk());

        // Validate the Category_document in the database
        List<Category_document> category_documentList = category_documentRepository.findAll();
        assertThat(category_documentList).hasSize(databaseSizeBeforeUpdate);
        Category_document testCategory_document = category_documentList.get(category_documentList.size() - 1);
        assertThat(testCategory_document.getCat_numero()).isEqualTo(UPDATED_CAT_NUMERO);
        assertThat(testCategory_document.getCat_nom()).isEqualTo(UPDATED_CAT_NOM);
    }

    @Test
    @Transactional
    public void updateNonExistingCategory_document() throws Exception {
        int databaseSizeBeforeUpdate = category_documentRepository.findAll().size();

        // Create the Category_document
        Category_documentDTO category_documentDTO = category_documentMapper.toDto(category_document);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restCategory_documentMockMvc.perform(put("/api/category-documents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(category_documentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Category_document in the database
        List<Category_document> category_documentList = category_documentRepository.findAll();
        assertThat(category_documentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteCategory_document() throws Exception {
        // Initialize the database
        category_documentRepository.saveAndFlush(category_document);

        int databaseSizeBeforeDelete = category_documentRepository.findAll().size();

        // Delete the category_document
        restCategory_documentMockMvc.perform(delete("/api/category-documents/{id}", category_document.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Category_document> category_documentList = category_documentRepository.findAll();
        assertThat(category_documentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category_document.class);
        Category_document category_document1 = new Category_document();
        category_document1.setId(1L);
        Category_document category_document2 = new Category_document();
        category_document2.setId(category_document1.getId());
        assertThat(category_document1).isEqualTo(category_document2);
        category_document2.setId(2L);
        assertThat(category_document1).isNotEqualTo(category_document2);
        category_document1.setId(null);
        assertThat(category_document1).isNotEqualTo(category_document2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(Category_documentDTO.class);
        Category_documentDTO category_documentDTO1 = new Category_documentDTO();
        category_documentDTO1.setId(1L);
        Category_documentDTO category_documentDTO2 = new Category_documentDTO();
        assertThat(category_documentDTO1).isNotEqualTo(category_documentDTO2);
        category_documentDTO2.setId(category_documentDTO1.getId());
        assertThat(category_documentDTO1).isEqualTo(category_documentDTO2);
        category_documentDTO2.setId(2L);
        assertThat(category_documentDTO1).isNotEqualTo(category_documentDTO2);
        category_documentDTO1.setId(null);
        assertThat(category_documentDTO1).isNotEqualTo(category_documentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(category_documentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(category_documentMapper.fromId(null)).isNull();
    }
}
