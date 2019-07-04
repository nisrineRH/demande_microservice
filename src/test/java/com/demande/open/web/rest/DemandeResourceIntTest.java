package com.demande.open.web.rest;

import com.demande.open.DemandeMicroServiceApp;

import com.demande.open.domain.Demande;
import com.demande.open.repository.DemandeRepository;
import com.demande.open.service.DemandeService;
import com.demande.open.service.dto.DemandeDTO;
import com.demande.open.service.mapper.DemandeMapper;
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
 * Test class for the DemandeResource REST controller.
 *
 * @see DemandeResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemandeMicroServiceApp.class)
public class DemandeResourceIntTest {

    private static final Integer DEFAULT_DM_NUMERO = 1;
    private static final Integer UPDATED_DM_NUMERO = 2;

    private static final String DEFAULT_DM_LIBELLE = "AAAAAAAAAA";
    private static final String UPDATED_DM_LIBELLE = "BBBBBBBBBB";

    private static final String DEFAULT_DM_STATU = "AAAAAAAAAA";
    private static final String UPDATED_DM_STATU = "BBBBBBBBBB";

    private static final String DEFAULT_DM_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_DM_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_DM_PRIORITE = "AAAAAAAAAA";
    private static final String UPDATED_DM_PRIORITE = "BBBBBBBBBB";

    private static final String DEFAULT_INTERVENANT = "AAAAAAAAAA";
    private static final String UPDATED_INTERVENANT = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_VISIBLE_SUR_INTERNET = false;
    private static final Boolean UPDATED_VISIBLE_SUR_INTERNET = true;

    private static final LocalDate DEFAULT_DATE_LIVRAISON_SOUHAITEE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LIVRAISON_SOUHAITEE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_ACCORD_DEVIS = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_ACCORD_DEVIS = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private DemandeRepository demandeRepository;

    @Autowired
    private DemandeMapper demandeMapper;

    @Autowired
    private DemandeService demandeService;

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

    private MockMvc restDemandeMockMvc;

    private Demande demande;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final DemandeResource demandeResource = new DemandeResource(demandeService);
        this.restDemandeMockMvc = MockMvcBuilders.standaloneSetup(demandeResource)
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
    public static Demande createEntity(EntityManager em) {
        Demande demande = new Demande()
            .dm_numero(DEFAULT_DM_NUMERO)
            .dm_libelle(DEFAULT_DM_LIBELLE)
            .dm_statu(DEFAULT_DM_STATU)
            .dm_type(DEFAULT_DM_TYPE)
            .dm_priorite(DEFAULT_DM_PRIORITE)
            .intervenant(DEFAULT_INTERVENANT)
            .description(DEFAULT_DESCRIPTION)
            .visibleSurInternet(DEFAULT_VISIBLE_SUR_INTERNET)
            .dateLivraisonSouhaitee(DEFAULT_DATE_LIVRAISON_SOUHAITEE)
            .dateAccordDevis(DEFAULT_DATE_ACCORD_DEVIS);
        return demande;
    }

    @Before
    public void initTest() {
        demande = createEntity(em);
    }

    @Test
    @Transactional
    public void createDemande() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isCreated());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate + 1);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getDm_numero()).isEqualTo(DEFAULT_DM_NUMERO);
        assertThat(testDemande.getDm_libelle()).isEqualTo(DEFAULT_DM_LIBELLE);
        assertThat(testDemande.getDm_statu()).isEqualTo(DEFAULT_DM_STATU);
        assertThat(testDemande.getDm_type()).isEqualTo(DEFAULT_DM_TYPE);
        assertThat(testDemande.getDm_priorite()).isEqualTo(DEFAULT_DM_PRIORITE);
        assertThat(testDemande.getIntervenant()).isEqualTo(DEFAULT_INTERVENANT);
        assertThat(testDemande.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testDemande.isVisibleSurInternet()).isEqualTo(DEFAULT_VISIBLE_SUR_INTERNET);
        assertThat(testDemande.getDateLivraisonSouhaitee()).isEqualTo(DEFAULT_DATE_LIVRAISON_SOUHAITEE);
        assertThat(testDemande.getDateAccordDevis()).isEqualTo(DEFAULT_DATE_ACCORD_DEVIS);
    }

    @Test
    @Transactional
    public void createDemandeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = demandeRepository.findAll().size();

        // Create the Demande with an existing ID
        demande.setId(1L);
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDemandeMockMvc.perform(post("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllDemandes() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get all the demandeList
        restDemandeMockMvc.perform(get("/api/demandes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(demande.getId().intValue())))
            .andExpect(jsonPath("$.[*].dm_numero").value(hasItem(DEFAULT_DM_NUMERO)))
            .andExpect(jsonPath("$.[*].dm_libelle").value(hasItem(DEFAULT_DM_LIBELLE.toString())))
            .andExpect(jsonPath("$.[*].dm_statu").value(hasItem(DEFAULT_DM_STATU.toString())))
            .andExpect(jsonPath("$.[*].dm_type").value(hasItem(DEFAULT_DM_TYPE.toString())))
            .andExpect(jsonPath("$.[*].dm_priorite").value(hasItem(DEFAULT_DM_PRIORITE.toString())))
            .andExpect(jsonPath("$.[*].intervenant").value(hasItem(DEFAULT_INTERVENANT.toString())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION.toString())))
            .andExpect(jsonPath("$.[*].visibleSurInternet").value(hasItem(DEFAULT_VISIBLE_SUR_INTERNET.booleanValue())))
            .andExpect(jsonPath("$.[*].dateLivraisonSouhaitee").value(hasItem(DEFAULT_DATE_LIVRAISON_SOUHAITEE.toString())))
            .andExpect(jsonPath("$.[*].dateAccordDevis").value(hasItem(DEFAULT_DATE_ACCORD_DEVIS.toString())));
    }
    
    @Test
    @Transactional
    public void getDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", demande.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(demande.getId().intValue()))
            .andExpect(jsonPath("$.dm_numero").value(DEFAULT_DM_NUMERO))
            .andExpect(jsonPath("$.dm_libelle").value(DEFAULT_DM_LIBELLE.toString()))
            .andExpect(jsonPath("$.dm_statu").value(DEFAULT_DM_STATU.toString()))
            .andExpect(jsonPath("$.dm_type").value(DEFAULT_DM_TYPE.toString()))
            .andExpect(jsonPath("$.dm_priorite").value(DEFAULT_DM_PRIORITE.toString()))
            .andExpect(jsonPath("$.intervenant").value(DEFAULT_INTERVENANT.toString()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION.toString()))
            .andExpect(jsonPath("$.visibleSurInternet").value(DEFAULT_VISIBLE_SUR_INTERNET.booleanValue()))
            .andExpect(jsonPath("$.dateLivraisonSouhaitee").value(DEFAULT_DATE_LIVRAISON_SOUHAITEE.toString()))
            .andExpect(jsonPath("$.dateAccordDevis").value(DEFAULT_DATE_ACCORD_DEVIS.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingDemande() throws Exception {
        // Get the demande
        restDemandeMockMvc.perform(get("/api/demandes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Update the demande
        Demande updatedDemande = demandeRepository.findById(demande.getId()).get();
        // Disconnect from session so that the updates on updatedDemande are not directly saved in db
        em.detach(updatedDemande);
        updatedDemande
            .dm_numero(UPDATED_DM_NUMERO)
            .dm_libelle(UPDATED_DM_LIBELLE)
            .dm_statu(UPDATED_DM_STATU)
            .dm_type(UPDATED_DM_TYPE)
            .dm_priorite(UPDATED_DM_PRIORITE)
            .intervenant(UPDATED_INTERVENANT)
            .description(UPDATED_DESCRIPTION)
            .visibleSurInternet(UPDATED_VISIBLE_SUR_INTERNET)
            .dateLivraisonSouhaitee(UPDATED_DATE_LIVRAISON_SOUHAITEE)
            .dateAccordDevis(UPDATED_DATE_ACCORD_DEVIS);
        DemandeDTO demandeDTO = demandeMapper.toDto(updatedDemande);

        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isOk());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
        Demande testDemande = demandeList.get(demandeList.size() - 1);
        assertThat(testDemande.getDm_numero()).isEqualTo(UPDATED_DM_NUMERO);
        assertThat(testDemande.getDm_libelle()).isEqualTo(UPDATED_DM_LIBELLE);
        assertThat(testDemande.getDm_statu()).isEqualTo(UPDATED_DM_STATU);
        assertThat(testDemande.getDm_type()).isEqualTo(UPDATED_DM_TYPE);
        assertThat(testDemande.getDm_priorite()).isEqualTo(UPDATED_DM_PRIORITE);
        assertThat(testDemande.getIntervenant()).isEqualTo(UPDATED_INTERVENANT);
        assertThat(testDemande.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testDemande.isVisibleSurInternet()).isEqualTo(UPDATED_VISIBLE_SUR_INTERNET);
        assertThat(testDemande.getDateLivraisonSouhaitee()).isEqualTo(UPDATED_DATE_LIVRAISON_SOUHAITEE);
        assertThat(testDemande.getDateAccordDevis()).isEqualTo(UPDATED_DATE_ACCORD_DEVIS);
    }

    @Test
    @Transactional
    public void updateNonExistingDemande() throws Exception {
        int databaseSizeBeforeUpdate = demandeRepository.findAll().size();

        // Create the Demande
        DemandeDTO demandeDTO = demandeMapper.toDto(demande);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDemandeMockMvc.perform(put("/api/demandes")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(demandeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Demande in the database
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDemande() throws Exception {
        // Initialize the database
        demandeRepository.saveAndFlush(demande);

        int databaseSizeBeforeDelete = demandeRepository.findAll().size();

        // Delete the demande
        restDemandeMockMvc.perform(delete("/api/demandes/{id}", demande.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Demande> demandeList = demandeRepository.findAll();
        assertThat(demandeList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Demande.class);
        Demande demande1 = new Demande();
        demande1.setId(1L);
        Demande demande2 = new Demande();
        demande2.setId(demande1.getId());
        assertThat(demande1).isEqualTo(demande2);
        demande2.setId(2L);
        assertThat(demande1).isNotEqualTo(demande2);
        demande1.setId(null);
        assertThat(demande1).isNotEqualTo(demande2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(DemandeDTO.class);
        DemandeDTO demandeDTO1 = new DemandeDTO();
        demandeDTO1.setId(1L);
        DemandeDTO demandeDTO2 = new DemandeDTO();
        assertThat(demandeDTO1).isNotEqualTo(demandeDTO2);
        demandeDTO2.setId(demandeDTO1.getId());
        assertThat(demandeDTO1).isEqualTo(demandeDTO2);
        demandeDTO2.setId(2L);
        assertThat(demandeDTO1).isNotEqualTo(demandeDTO2);
        demandeDTO1.setId(null);
        assertThat(demandeDTO1).isNotEqualTo(demandeDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(demandeMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(demandeMapper.fromId(null)).isNull();
    }
}
