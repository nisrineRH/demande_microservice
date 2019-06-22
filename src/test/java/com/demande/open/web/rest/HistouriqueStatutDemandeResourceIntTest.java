package com.demande.open.web.rest;

import com.demande.open.DemandeMicroServiceApp;

import com.demande.open.domain.HistouriqueStatutDemande;
import com.demande.open.repository.HistouriqueStatutDemandeRepository;
import com.demande.open.service.HistouriqueStatutDemandeService;
import com.demande.open.service.dto.HistouriqueStatutDemandeDTO;
import com.demande.open.service.mapper.HistouriqueStatutDemandeMapper;
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
 * Test class for the HistouriqueStatutDemandeResource REST controller.
 *
 * @see HistouriqueStatutDemandeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemandeMicroServiceApp.class)
public class HistouriqueStatutDemandeResourceIntTest {

    private static final LocalDate DEFAULT_USER_MODIFICATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_USER_MODIFICATION = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_MSG_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_MSG_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DESINATAIRE_DU_MAIL = "AAAAAAAAAA";
    private static final String UPDATED_DESINATAIRE_DU_MAIL = "BBBBBBBBBB";

    private static final String DEFAULT_DM_STATUT = "AAAAAAAAAA";
    private static final String UPDATED_DM_STATUT = "BBBBBBBBBB";

    @Autowired
    private HistouriqueStatutDemandeRepository histouriqueStatutDemandeRepository;

    @Autowired
    private HistouriqueStatutDemandeMapper histouriqueStatutDemandeMapper;

    @Autowired
    private HistouriqueStatutDemandeService histouriqueStatutDemandeService;

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

    private MockMvc restHistouriqueStatutDemandeMockMvc;

    private HistouriqueStatutDemande histouriqueStatutDemande;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final HistouriqueStatutDemandeResource histouriqueStatutDemandeResource = new HistouriqueStatutDemandeResource(histouriqueStatutDemandeService);
        this.restHistouriqueStatutDemandeMockMvc = MockMvcBuilders.standaloneSetup(histouriqueStatutDemandeResource)
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
    public static HistouriqueStatutDemande createEntity(EntityManager em) {
        HistouriqueStatutDemande histouriqueStatutDemande = new HistouriqueStatutDemande()
            .userModification(DEFAULT_USER_MODIFICATION)
            .msgMail(DEFAULT_MSG_MAIL)
            .desinataireDuMail(DEFAULT_DESINATAIRE_DU_MAIL)
            .dm_statut(DEFAULT_DM_STATUT);
        return histouriqueStatutDemande;
    }

    @Before
    public void initTest() {
        histouriqueStatutDemande = createEntity(em);
    }

    @Test
    @Transactional
    public void createHistouriqueStatutDemande() throws Exception {
        int databaseSizeBeforeCreate = histouriqueStatutDemandeRepository.findAll().size();

        // Create the HistouriqueStatutDemande
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO = histouriqueStatutDemandeMapper.toDto(histouriqueStatutDemande);
        restHistouriqueStatutDemandeMockMvc.perform(post("/api/histourique-statut-demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(histouriqueStatutDemandeDTO)))
            .andExpect(status().isCreated());

        // Validate the HistouriqueStatutDemande in the database
        List<HistouriqueStatutDemande> histouriqueStatutDemandeList = histouriqueStatutDemandeRepository.findAll();
        assertThat(histouriqueStatutDemandeList).hasSize(databaseSizeBeforeCreate + 1);
        HistouriqueStatutDemande testHistouriqueStatutDemande = histouriqueStatutDemandeList.get(histouriqueStatutDemandeList.size() - 1);
        assertThat(testHistouriqueStatutDemande.getUserModification()).isEqualTo(DEFAULT_USER_MODIFICATION);
        assertThat(testHistouriqueStatutDemande.getMsgMail()).isEqualTo(DEFAULT_MSG_MAIL);
        assertThat(testHistouriqueStatutDemande.getDesinataireDuMail()).isEqualTo(DEFAULT_DESINATAIRE_DU_MAIL);
        assertThat(testHistouriqueStatutDemande.getDm_statut()).isEqualTo(DEFAULT_DM_STATUT);
    }

    @Test
    @Transactional
    public void createHistouriqueStatutDemandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = histouriqueStatutDemandeRepository.findAll().size();

        // Create the HistouriqueStatutDemande with an existing ID
        histouriqueStatutDemande.setId(1L);
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO = histouriqueStatutDemandeMapper.toDto(histouriqueStatutDemande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restHistouriqueStatutDemandeMockMvc.perform(post("/api/histourique-statut-demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(histouriqueStatutDemandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistouriqueStatutDemande in the database
        List<HistouriqueStatutDemande> histouriqueStatutDemandeList = histouriqueStatutDemandeRepository.findAll();
        assertThat(histouriqueStatutDemandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllHistouriqueStatutDemandes() throws Exception {
        // Initialize the database
        histouriqueStatutDemandeRepository.saveAndFlush(histouriqueStatutDemande);

        // Get all the histouriqueStatutDemandeList
        restHistouriqueStatutDemandeMockMvc.perform(get("/api/histourique-statut-demandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(histouriqueStatutDemande.getId().intValue())))
            .andExpect(jsonPath("$.[*].userModification").value(hasItem(DEFAULT_USER_MODIFICATION.toString())))
            .andExpect(jsonPath("$.[*].msgMail").value(hasItem(DEFAULT_MSG_MAIL.toString())))
            .andExpect(jsonPath("$.[*].desinataireDuMail").value(hasItem(DEFAULT_DESINATAIRE_DU_MAIL.toString())))
            .andExpect(jsonPath("$.[*].dm_statut").value(hasItem(DEFAULT_DM_STATUT.toString())));
    }
    
    @Test
    @Transactional
    public void getHistouriqueStatutDemande() throws Exception {
        // Initialize the database
        histouriqueStatutDemandeRepository.saveAndFlush(histouriqueStatutDemande);

        // Get the histouriqueStatutDemande
        restHistouriqueStatutDemandeMockMvc.perform(get("/api/histourique-statut-demandes/{id}", histouriqueStatutDemande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(histouriqueStatutDemande.getId().intValue()))
            .andExpect(jsonPath("$.userModification").value(DEFAULT_USER_MODIFICATION.toString()))
            .andExpect(jsonPath("$.msgMail").value(DEFAULT_MSG_MAIL.toString()))
            .andExpect(jsonPath("$.desinataireDuMail").value(DEFAULT_DESINATAIRE_DU_MAIL.toString()))
            .andExpect(jsonPath("$.dm_statut").value(DEFAULT_DM_STATUT.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingHistouriqueStatutDemande() throws Exception {
        // Get the histouriqueStatutDemande
        restHistouriqueStatutDemandeMockMvc.perform(get("/api/histourique-statut-demandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateHistouriqueStatutDemande() throws Exception {
        // Initialize the database
        histouriqueStatutDemandeRepository.saveAndFlush(histouriqueStatutDemande);

        int databaseSizeBeforeUpdate = histouriqueStatutDemandeRepository.findAll().size();

        // Update the histouriqueStatutDemande
        HistouriqueStatutDemande updatedHistouriqueStatutDemande = histouriqueStatutDemandeRepository.findById(histouriqueStatutDemande.getId()).get();
        // Disconnect from session so that the updates on updatedHistouriqueStatutDemande are not directly saved in db
        em.detach(updatedHistouriqueStatutDemande);
        updatedHistouriqueStatutDemande
            .userModification(UPDATED_USER_MODIFICATION)
            .msgMail(UPDATED_MSG_MAIL)
            .desinataireDuMail(UPDATED_DESINATAIRE_DU_MAIL)
            .dm_statut(UPDATED_DM_STATUT);
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO = histouriqueStatutDemandeMapper.toDto(updatedHistouriqueStatutDemande);

        restHistouriqueStatutDemandeMockMvc.perform(put("/api/histourique-statut-demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(histouriqueStatutDemandeDTO)))
            .andExpect(status().isOk());

        // Validate the HistouriqueStatutDemande in the database
        List<HistouriqueStatutDemande> histouriqueStatutDemandeList = histouriqueStatutDemandeRepository.findAll();
        assertThat(histouriqueStatutDemandeList).hasSize(databaseSizeBeforeUpdate);
        HistouriqueStatutDemande testHistouriqueStatutDemande = histouriqueStatutDemandeList.get(histouriqueStatutDemandeList.size() - 1);
        assertThat(testHistouriqueStatutDemande.getUserModification()).isEqualTo(UPDATED_USER_MODIFICATION);
        assertThat(testHistouriqueStatutDemande.getMsgMail()).isEqualTo(UPDATED_MSG_MAIL);
        assertThat(testHistouriqueStatutDemande.getDesinataireDuMail()).isEqualTo(UPDATED_DESINATAIRE_DU_MAIL);
        assertThat(testHistouriqueStatutDemande.getDm_statut()).isEqualTo(UPDATED_DM_STATUT);
    }

    @Test
    @Transactional
    public void updateNonExistingHistouriqueStatutDemande() throws Exception {
        int databaseSizeBeforeUpdate = histouriqueStatutDemandeRepository.findAll().size();

        // Create the HistouriqueStatutDemande
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO = histouriqueStatutDemandeMapper.toDto(histouriqueStatutDemande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restHistouriqueStatutDemandeMockMvc.perform(put("/api/histourique-statut-demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(histouriqueStatutDemandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the HistouriqueStatutDemande in the database
        List<HistouriqueStatutDemande> histouriqueStatutDemandeList = histouriqueStatutDemandeRepository.findAll();
        assertThat(histouriqueStatutDemandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteHistouriqueStatutDemande() throws Exception {
        // Initialize the database
        histouriqueStatutDemandeRepository.saveAndFlush(histouriqueStatutDemande);

        int databaseSizeBeforeDelete = histouriqueStatutDemandeRepository.findAll().size();

        // Delete the histouriqueStatutDemande
        restHistouriqueStatutDemandeMockMvc.perform(delete("/api/histourique-statut-demandes/{id}", histouriqueStatutDemande.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<HistouriqueStatutDemande> histouriqueStatutDemandeList = histouriqueStatutDemandeRepository.findAll();
        assertThat(histouriqueStatutDemandeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistouriqueStatutDemande.class);
        HistouriqueStatutDemande histouriqueStatutDemande1 = new HistouriqueStatutDemande();
        histouriqueStatutDemande1.setId(1L);
        HistouriqueStatutDemande histouriqueStatutDemande2 = new HistouriqueStatutDemande();
        histouriqueStatutDemande2.setId(histouriqueStatutDemande1.getId());
        assertThat(histouriqueStatutDemande1).isEqualTo(histouriqueStatutDemande2);
        histouriqueStatutDemande2.setId(2L);
        assertThat(histouriqueStatutDemande1).isNotEqualTo(histouriqueStatutDemande2);
        histouriqueStatutDemande1.setId(null);
        assertThat(histouriqueStatutDemande1).isNotEqualTo(histouriqueStatutDemande2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(HistouriqueStatutDemandeDTO.class);
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO1 = new HistouriqueStatutDemandeDTO();
        histouriqueStatutDemandeDTO1.setId(1L);
        HistouriqueStatutDemandeDTO histouriqueStatutDemandeDTO2 = new HistouriqueStatutDemandeDTO();
        assertThat(histouriqueStatutDemandeDTO1).isNotEqualTo(histouriqueStatutDemandeDTO2);
        histouriqueStatutDemandeDTO2.setId(histouriqueStatutDemandeDTO1.getId());
        assertThat(histouriqueStatutDemandeDTO1).isEqualTo(histouriqueStatutDemandeDTO2);
        histouriqueStatutDemandeDTO2.setId(2L);
        assertThat(histouriqueStatutDemandeDTO1).isNotEqualTo(histouriqueStatutDemandeDTO2);
        histouriqueStatutDemandeDTO1.setId(null);
        assertThat(histouriqueStatutDemandeDTO1).isNotEqualTo(histouriqueStatutDemandeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(histouriqueStatutDemandeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(histouriqueStatutDemandeMapper.fromId(null)).isNull();
    }
}
