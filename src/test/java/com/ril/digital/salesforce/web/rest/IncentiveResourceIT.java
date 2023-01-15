package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.Incentive;
import com.ril.digital.salesforce.repository.IncentiveRepository;
import com.ril.digital.salesforce.service.dto.IncentiveDTO;
import com.ril.digital.salesforce.service.mapper.IncentiveMapper;
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
 * Integration tests for the {@link IncentiveResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class IncentiveResourceIT {

    private static final Float DEFAULT_ACHIVEMENT_PERCENT = 1F;
    private static final Float UPDATED_ACHIVEMENT_PERCENT = 2F;

    private static final Float DEFAULT_INCENTIVE_VALUE = 1F;
    private static final Float UPDATED_INCENTIVE_VALUE = 2F;

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

    private static final String ENTITY_API_URL = "/api/incentives";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private IncentiveRepository incentiveRepository;

    @Autowired
    private IncentiveMapper incentiveMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restIncentiveMockMvc;

    private Incentive incentive;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Incentive createEntity(EntityManager em) {
        Incentive incentive = new Incentive()
            .achivementPercent(DEFAULT_ACHIVEMENT_PERCENT)
            .incentiveValue(DEFAULT_INCENTIVE_VALUE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return incentive;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Incentive createUpdatedEntity(EntityManager em) {
        Incentive incentive = new Incentive()
            .achivementPercent(UPDATED_ACHIVEMENT_PERCENT)
            .incentiveValue(UPDATED_INCENTIVE_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return incentive;
    }

    @BeforeEach
    public void initTest() {
        incentive = createEntity(em);
    }

    @Test
    @Transactional
    void createIncentive() throws Exception {
        int databaseSizeBeforeCreate = incentiveRepository.findAll().size();
        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);
        restIncentiveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveDTO)))
            .andExpect(status().isCreated());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeCreate + 1);
        Incentive testIncentive = incentiveList.get(incentiveList.size() - 1);
        assertThat(testIncentive.getAchivementPercent()).isEqualTo(DEFAULT_ACHIVEMENT_PERCENT);
        assertThat(testIncentive.getIncentiveValue()).isEqualTo(DEFAULT_INCENTIVE_VALUE);
        assertThat(testIncentive.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testIncentive.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testIncentive.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncentive.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncentive.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createIncentiveWithExistingId() throws Exception {
        // Create the Incentive with an existing ID
        incentive.setId(1L);
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        int databaseSizeBeforeCreate = incentiveRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restIncentiveMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllIncentives() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        // Get all the incentiveList
        restIncentiveMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(incentive.getId().intValue())))
            .andExpect(jsonPath("$.[*].achivementPercent").value(hasItem(DEFAULT_ACHIVEMENT_PERCENT.doubleValue())))
            .andExpect(jsonPath("$.[*].incentiveValue").value(hasItem(DEFAULT_INCENTIVE_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getIncentive() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        // Get the incentive
        restIncentiveMockMvc
            .perform(get(ENTITY_API_URL_ID, incentive.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(incentive.getId().intValue()))
            .andExpect(jsonPath("$.achivementPercent").value(DEFAULT_ACHIVEMENT_PERCENT.doubleValue()))
            .andExpect(jsonPath("$.incentiveValue").value(DEFAULT_INCENTIVE_VALUE.doubleValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingIncentive() throws Exception {
        // Get the incentive
        restIncentiveMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingIncentive() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();

        // Update the incentive
        Incentive updatedIncentive = incentiveRepository.findById(incentive.getId()).get();
        // Disconnect from session so that the updates on updatedIncentive are not directly saved in db
        em.detach(updatedIncentive);
        updatedIncentive
            .achivementPercent(UPDATED_ACHIVEMENT_PERCENT)
            .incentiveValue(UPDATED_INCENTIVE_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(updatedIncentive);

        restIncentiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incentiveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isOk());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
        Incentive testIncentive = incentiveList.get(incentiveList.size() - 1);
        assertThat(testIncentive.getAchivementPercent()).isEqualTo(UPDATED_ACHIVEMENT_PERCENT);
        assertThat(testIncentive.getIncentiveValue()).isEqualTo(UPDATED_INCENTIVE_VALUE);
        assertThat(testIncentive.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testIncentive.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentive.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncentive.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncentive.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, incentiveDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(incentiveDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateIncentiveWithPatch() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();

        // Update the incentive using partial update
        Incentive partialUpdatedIncentive = new Incentive();
        partialUpdatedIncentive.setId(incentive.getId());

        partialUpdatedIncentive
            .achivementPercent(UPDATED_ACHIVEMENT_PERCENT)
            .incentiveValue(UPDATED_INCENTIVE_VALUE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        restIncentiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncentive.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncentive))
            )
            .andExpect(status().isOk());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
        Incentive testIncentive = incentiveList.get(incentiveList.size() - 1);
        assertThat(testIncentive.getAchivementPercent()).isEqualTo(UPDATED_ACHIVEMENT_PERCENT);
        assertThat(testIncentive.getIncentiveValue()).isEqualTo(UPDATED_INCENTIVE_VALUE);
        assertThat(testIncentive.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testIncentive.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentive.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testIncentive.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testIncentive.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateIncentiveWithPatch() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();

        // Update the incentive using partial update
        Incentive partialUpdatedIncentive = new Incentive();
        partialUpdatedIncentive.setId(incentive.getId());

        partialUpdatedIncentive
            .achivementPercent(UPDATED_ACHIVEMENT_PERCENT)
            .incentiveValue(UPDATED_INCENTIVE_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restIncentiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedIncentive.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedIncentive))
            )
            .andExpect(status().isOk());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
        Incentive testIncentive = incentiveList.get(incentiveList.size() - 1);
        assertThat(testIncentive.getAchivementPercent()).isEqualTo(UPDATED_ACHIVEMENT_PERCENT);
        assertThat(testIncentive.getIncentiveValue()).isEqualTo(UPDATED_INCENTIVE_VALUE);
        assertThat(testIncentive.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testIncentive.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testIncentive.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testIncentive.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testIncentive.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, incentiveDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamIncentive() throws Exception {
        int databaseSizeBeforeUpdate = incentiveRepository.findAll().size();
        incentive.setId(count.incrementAndGet());

        // Create the Incentive
        IncentiveDTO incentiveDTO = incentiveMapper.toDto(incentive);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restIncentiveMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(incentiveDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Incentive in the database
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteIncentive() throws Exception {
        // Initialize the database
        incentiveRepository.saveAndFlush(incentive);

        int databaseSizeBeforeDelete = incentiveRepository.findAll().size();

        // Delete the incentive
        restIncentiveMockMvc
            .perform(delete(ENTITY_API_URL_ID, incentive.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Incentive> incentiveList = incentiveRepository.findAll();
        assertThat(incentiveList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
