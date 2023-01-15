package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.IncentiveScheme;
import com.ril.digital.salesforce.domain.enumeration.IncentiveSchemeType;
import com.ril.digital.salesforce.repository.IncentiveSchemeRepository;
import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
import com.ril.digital.salesforce.service.mapper.IncentiveSchemeMapper;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link IncentiveSchemeResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IncentiveSchemeResourceIT {

    private static final IncentiveSchemeType DEFAULT_TYPE = IncentiveSchemeType.Percent;
    private static final IncentiveSchemeType UPDATED_TYPE = IncentiveSchemeType.Absolute;

    private static final Float DEFAULT_MIN_ACHIVEMENT_PERCENT = 1F;
    private static final Float UPDATED_MIN_ACHIVEMENT_PERCENT = 2F;

    private static final Float DEFAULT_MAX_ACHIVEMENT_PERCENT = 1F;
    private static final Float UPDATED_MAX_ACHIVEMENT_PERCENT = 2F;

    private static final Float DEFAULT_VALUE = 1F;
    private static final Float UPDATED_VALUE = 2F;

    private static final Boolean DEFAULT_IS_ACTIVE = false;
    private static final Boolean UPDATED_IS_ACTIVE = true;

    private static final LocalDate DEFAULT_CREATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_UPDATED_AT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_UPDATED_AT = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/incentive-schemes";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IncentiveSchemeRepository incentiveSchemeRepository;

    @Autowired
    private IncentiveSchemeMapper incentiveSchemeMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIncentiveSchemeMockMvc;

    private IncentiveScheme incentiveScheme;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncentiveScheme createEntity(EntityManager em) {
        IncentiveScheme incentiveScheme = new IncentiveScheme()
            .type(DEFAULT_TYPE)
            .minAchivementPercent(DEFAULT_MIN_ACHIVEMENT_PERCENT)
            .maxAchivementPercent(DEFAULT_MAX_ACHIVEMENT_PERCENT)
            .value(DEFAULT_VALUE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return incentiveScheme;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static IncentiveScheme createUpdatedEntity(EntityManager em) {
        IncentiveScheme incentiveScheme = new IncentiveScheme()
            .type(UPDATED_TYPE)
            .minAchivementPercent(UPDATED_MIN_ACHIVEMENT_PERCENT)
            .maxAchivementPercent(UPDATED_MAX_ACHIVEMENT_PERCENT)
            .value(UPDATED_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return incentiveScheme;
    }

    @BeforeEach
    public void initTest() {
        incentiveScheme = createEntity(em);
    }

    @Test
    @Transactional
    void createIncentiveScheme() throws Exception {
        int databaseSizeBeforeCreate = incentiveSchemeRepository.findAll().size();
        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);
        restIncentiveSchemeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isCreated());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeCreate + 1);
        IncentiveScheme testIncentiveScheme = incentiveSchemeList.get(incentiveSchemeList.size() - 1);
        assertThat(testIncentiveScheme.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testIncentiveScheme.getMinAchivementPercent()).isEqualTo(DEFAULT_MIN_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getMaxAchivementPercent()).isEqualTo(DEFAULT_MAX_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testIncentiveScheme.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testIncentiveScheme.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncentiveScheme.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncentiveScheme.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncentiveScheme.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createIncentiveSchemeWithExistingId() throws Exception {
        // Create the IncentiveScheme with an existing ID
        incentiveScheme.setId(1L);
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        int databaseSizeBeforeCreate = incentiveSchemeRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncentiveSchemeMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIncentiveSchemes() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        // Get all the incentiveSchemeList
        restIncentiveSchemeMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incentiveScheme.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].minAchivementPercent").value(hasItem(DEFAULT_MIN_ACHIVEMENT_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].maxAchivementPercent").value(hasItem(DEFAULT_MAX_ACHIVEMENT_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].value").value(hasItem(DEFAULT_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getIncentiveScheme() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        // Get the incentiveScheme
        restIncentiveSchemeMockMvc
            .perform(get(ENTITY_API_URL_ID, incentiveScheme.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(incentiveScheme.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.minAchivementPercent").value(DEFAULT_MIN_ACHIVEMENT_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.maxAchivementPercent").value(DEFAULT_MAX_ACHIVEMENT_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.value").value(DEFAULT_VALUE.doubleValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingIncentiveScheme() throws Exception {
        // Get the incentiveScheme
        restIncentiveSchemeMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIncentiveScheme() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();

        // Update the incentiveScheme
        IncentiveScheme updatedIncentiveScheme = incentiveSchemeRepository.findById(incentiveScheme.getId()).get();
        // Disconnect from session so that the updates on updatedIncentiveScheme are not directly saved in db
        em.detach(updatedIncentiveScheme);
        updatedIncentiveScheme
            .type(UPDATED_TYPE)
            .minAchivementPercent(UPDATED_MIN_ACHIVEMENT_PERCENT)
            .maxAchivementPercent(UPDATED_MAX_ACHIVEMENT_PERCENT)
            .value(UPDATED_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(updatedIncentiveScheme);

        restIncentiveSchemeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incentiveSchemeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isOk());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
        IncentiveScheme testIncentiveScheme = incentiveSchemeList.get(incentiveSchemeList.size() - 1);
        assertThat(testIncentiveScheme.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testIncentiveScheme.getMinAchivementPercent()).isEqualTo(UPDATED_MIN_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getMaxAchivementPercent()).isEqualTo(UPDATED_MAX_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testIncentiveScheme.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testIncentiveScheme.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentiveScheme.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncentiveScheme.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncentiveScheme.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incentiveSchemeDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIncentiveSchemeWithPatch() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();

        // Update the incentiveScheme using partial update
        IncentiveScheme partialUpdatedIncentiveScheme = new IncentiveScheme();
        partialUpdatedIncentiveScheme.setId(incentiveScheme.getId());

        partialUpdatedIncentiveScheme.type(UPDATED_TYPE).minAchivementPercent(UPDATED_MIN_ACHIVEMENT_PERCENT).createdAt(UPDATED_CREATED_AT);

        restIncentiveSchemeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncentiveScheme.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncentiveScheme))
            )
            .andExpect(status().isOk());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
        IncentiveScheme testIncentiveScheme = incentiveSchemeList.get(incentiveSchemeList.size() - 1);
        assertThat(testIncentiveScheme.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testIncentiveScheme.getMinAchivementPercent()).isEqualTo(UPDATED_MIN_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getMaxAchivementPercent()).isEqualTo(DEFAULT_MAX_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getValue()).isEqualTo(DEFAULT_VALUE);
        assertThat(testIncentiveScheme.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testIncentiveScheme.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentiveScheme.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncentiveScheme.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncentiveScheme.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateIncentiveSchemeWithPatch() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();

        // Update the incentiveScheme using partial update
        IncentiveScheme partialUpdatedIncentiveScheme = new IncentiveScheme();
        partialUpdatedIncentiveScheme.setId(incentiveScheme.getId());

        partialUpdatedIncentiveScheme
            .type(UPDATED_TYPE)
            .minAchivementPercent(UPDATED_MIN_ACHIVEMENT_PERCENT)
            .maxAchivementPercent(UPDATED_MAX_ACHIVEMENT_PERCENT)
            .value(UPDATED_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restIncentiveSchemeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncentiveScheme.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncentiveScheme))
            )
            .andExpect(status().isOk());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
        IncentiveScheme testIncentiveScheme = incentiveSchemeList.get(incentiveSchemeList.size() - 1);
        assertThat(testIncentiveScheme.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testIncentiveScheme.getMinAchivementPercent()).isEqualTo(UPDATED_MIN_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getMaxAchivementPercent()).isEqualTo(UPDATED_MAX_ACHIVEMENT_PERCENT);
        assertThat(testIncentiveScheme.getValue()).isEqualTo(UPDATED_VALUE);
        assertThat(testIncentiveScheme.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testIncentiveScheme.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentiveScheme.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncentiveScheme.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncentiveScheme.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, incentiveSchemeDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIncentiveScheme() throws Exception {
        int databaseSizeBeforeUpdate = incentiveSchemeRepository.findAll().size();
        incentiveScheme.setId(count.incrementAndGet());

        // Create the IncentiveScheme
        IncentiveSchemeDTO incentiveSchemeDTO = incentiveSchemeMapper.toDto(incentiveScheme);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveSchemeMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incentiveSchemeDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the IncentiveScheme in the database
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIncentiveScheme() throws Exception {
        // Initialize the database
        incentiveSchemeRepository.saveAndFlush(incentiveScheme);

        int databaseSizeBeforeDelete = incentiveSchemeRepository.findAll().size();

        // Delete the incentiveScheme
        restIncentiveSchemeMockMvc
            .perform(delete(ENTITY_API_URL_ID, incentiveScheme.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<IncentiveScheme> incentiveSchemeList = incentiveSchemeRepository.findAll();
        assertThat(incentiveSchemeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
