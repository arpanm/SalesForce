package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.SalesTarget;
import com.ril.digital.salesforce.domain.enumeration.SalesTargetType;
import com.ril.digital.salesforce.repository.SalesTargetRepository;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import com.ril.digital.salesforce.service.mapper.SalesTargetMapper;
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
 * Integration tests for the {@link SalesTargetResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesTargetResourceIT {

    private static final SalesTargetType DEFAULT_TYPE = SalesTargetType.Revenue;
    private static final SalesTargetType UPDATED_TYPE = SalesTargetType.OrderCount;

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final Float DEFAULT_TARGET_VALUE = 1F;
    private static final Float UPDATED_TARGET_VALUE = 2F;

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

    private static final String ENTITY_API_URL = "/api/sales-targets";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesTargetRepository salesTargetRepository;

    @Autowired
    private SalesTargetMapper salesTargetMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesTargetMockMvc;

    private SalesTarget salesTarget;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesTarget createEntity(EntityManager em) {
        SalesTarget salesTarget = new SalesTarget()
            .type(DEFAULT_TYPE)
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .targetValue(DEFAULT_TARGET_VALUE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return salesTarget;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesTarget createUpdatedEntity(EntityManager em) {
        SalesTarget salesTarget = new SalesTarget()
            .type(UPDATED_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .targetValue(UPDATED_TARGET_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return salesTarget;
    }

    @BeforeEach
    public void initTest() {
        salesTarget = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesTarget() throws Exception {
        int databaseSizeBeforeCreate = salesTargetRepository.findAll().size();
        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);
        restSalesTargetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeCreate + 1);
        SalesTarget testSalesTarget = salesTargetList.get(salesTargetList.size() - 1);
        assertThat(testSalesTarget.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testSalesTarget.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testSalesTarget.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testSalesTarget.getTargetValue()).isEqualTo(DEFAULT_TARGET_VALUE);
        assertThat(testSalesTarget.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSalesTarget.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSalesTarget.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testSalesTarget.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSalesTarget.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createSalesTargetWithExistingId() throws Exception {
        // Create the SalesTarget with an existing ID
        salesTarget.setId(1L);
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        int databaseSizeBeforeCreate = salesTargetRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesTargetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkStartDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesTargetRepository.findAll().size();
        // set the field null
        salesTarget.setStartDate(null);

        // Create the SalesTarget, which fails.
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        restSalesTargetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEndDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesTargetRepository.findAll().size();
        // set the field null
        salesTarget.setEndDate(null);

        // Create the SalesTarget, which fails.
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        restSalesTargetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkTargetValueIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesTargetRepository.findAll().size();
        // set the field null
        salesTarget.setTargetValue(null);

        // Create the SalesTarget, which fails.
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        restSalesTargetMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSalesTargets() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        // Get all the salesTargetList
        restSalesTargetMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesTarget.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.toString())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].targetValue").value(hasItem(DEFAULT_TARGET_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getSalesTarget() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        // Get the salesTarget
        restSalesTargetMockMvc
            .perform(get(ENTITY_API_URL_ID, salesTarget.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesTarget.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.toString()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.targetValue").value(DEFAULT_TARGET_VALUE.doubleValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingSalesTarget() throws Exception {
        // Get the salesTarget
        restSalesTargetMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesTarget() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();

        // Update the salesTarget
        SalesTarget updatedSalesTarget = salesTargetRepository.findById(salesTarget.getId()).get();
        // Disconnect from session so that the updates on updatedSalesTarget are not directly saved in db
        em.detach(updatedSalesTarget);
        updatedSalesTarget
            .type(UPDATED_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .targetValue(UPDATED_TARGET_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(updatedSalesTarget);

        restSalesTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesTargetDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
        SalesTarget testSalesTarget = salesTargetList.get(salesTargetList.size() - 1);
        assertThat(testSalesTarget.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSalesTarget.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSalesTarget.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSalesTarget.getTargetValue()).isEqualTo(UPDATED_TARGET_VALUE);
        assertThat(testSalesTarget.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesTarget.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesTarget.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesTarget.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTarget.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesTargetDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesTargetWithPatch() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();

        // Update the salesTarget using partial update
        SalesTarget partialUpdatedSalesTarget = new SalesTarget();
        partialUpdatedSalesTarget.setId(salesTarget.getId());

        partialUpdatedSalesTarget
            .type(UPDATED_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .targetValue(UPDATED_TARGET_VALUE)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restSalesTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesTarget.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesTarget))
            )
            .andExpect(status().isOk());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
        SalesTarget testSalesTarget = salesTargetList.get(salesTargetList.size() - 1);
        assertThat(testSalesTarget.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSalesTarget.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSalesTarget.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSalesTarget.getTargetValue()).isEqualTo(UPDATED_TARGET_VALUE);
        assertThat(testSalesTarget.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSalesTarget.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesTarget.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testSalesTarget.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTarget.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateSalesTargetWithPatch() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();

        // Update the salesTarget using partial update
        SalesTarget partialUpdatedSalesTarget = new SalesTarget();
        partialUpdatedSalesTarget.setId(salesTarget.getId());

        partialUpdatedSalesTarget
            .type(UPDATED_TYPE)
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .targetValue(UPDATED_TARGET_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restSalesTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesTarget.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesTarget))
            )
            .andExpect(status().isOk());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
        SalesTarget testSalesTarget = salesTargetList.get(salesTargetList.size() - 1);
        assertThat(testSalesTarget.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testSalesTarget.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testSalesTarget.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testSalesTarget.getTargetValue()).isEqualTo(UPDATED_TARGET_VALUE);
        assertThat(testSalesTarget.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesTarget.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesTarget.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesTarget.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTarget.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesTargetDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesTarget() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetRepository.findAll().size();
        salesTarget.setId(count.incrementAndGet());

        // Create the SalesTarget
        SalesTargetDTO salesTargetDTO = salesTargetMapper.toDto(salesTarget);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(salesTargetDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesTarget in the database
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesTarget() throws Exception {
        // Initialize the database
        salesTargetRepository.saveAndFlush(salesTarget);

        int databaseSizeBeforeDelete = salesTargetRepository.findAll().size();

        // Delete the salesTarget
        restSalesTargetMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesTarget.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesTarget> salesTargetList = salesTargetRepository.findAll();
        assertThat(salesTargetList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
