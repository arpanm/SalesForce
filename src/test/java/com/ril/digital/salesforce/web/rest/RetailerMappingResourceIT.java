package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.RetailerMapping;
import com.ril.digital.salesforce.repository.RetailerMappingRepository;
import com.ril.digital.salesforce.service.dto.RetailerMappingDTO;
import com.ril.digital.salesforce.service.mapper.RetailerMappingMapper;
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
 * Integration tests for the {@link RetailerMappingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RetailerMappingResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PRM_ID = "AAAAAAAAAA";
    private static final String UPDATED_PRM_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AGENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_AGENT_CODE = "BBBBBBBBBB";

    private static final Long DEFAULT_PINCODE = 1L;
    private static final Long UPDATED_PINCODE = 2L;

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_REGION = "AAAAAAAAAA";
    private static final String UPDATED_REGION = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_REGISTED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_REGISTED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MAPPED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MAPPED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_MAPPED_TILL = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_MAPPED_TILL = LocalDate.now(ZoneId.systemDefault());

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

    private static final String ENTITY_API_URL = "/api/retailer-mappings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RetailerMappingRepository retailerMappingRepository;

    @Autowired
    private RetailerMappingMapper retailerMappingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRetailerMappingMockMvc;

    private RetailerMapping retailerMapping;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetailerMapping createEntity(EntityManager em) {
        RetailerMapping retailerMapping = new RetailerMapping()
            .name(DEFAULT_NAME)
            .prmId(DEFAULT_PRM_ID)
            .agentCode(DEFAULT_AGENT_CODE)
            .pincode(DEFAULT_PINCODE)
            .state(DEFAULT_STATE)
            .region(DEFAULT_REGION)
            .registedOn(DEFAULT_REGISTED_ON)
            .mappedOn(DEFAULT_MAPPED_ON)
            .mappedTill(DEFAULT_MAPPED_TILL)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return retailerMapping;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RetailerMapping createUpdatedEntity(EntityManager em) {
        RetailerMapping retailerMapping = new RetailerMapping()
            .name(UPDATED_NAME)
            .prmId(UPDATED_PRM_ID)
            .agentCode(UPDATED_AGENT_CODE)
            .pincode(UPDATED_PINCODE)
            .state(UPDATED_STATE)
            .region(UPDATED_REGION)
            .registedOn(UPDATED_REGISTED_ON)
            .mappedOn(UPDATED_MAPPED_ON)
            .mappedTill(UPDATED_MAPPED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return retailerMapping;
    }

    @BeforeEach
    public void initTest() {
        retailerMapping = createEntity(em);
    }

    @Test
    @Transactional
    void createRetailerMapping() throws Exception {
        int databaseSizeBeforeCreate = retailerMappingRepository.findAll().size();
        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);
        restRetailerMappingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeCreate + 1);
        RetailerMapping testRetailerMapping = retailerMappingList.get(retailerMappingList.size() - 1);
        assertThat(testRetailerMapping.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRetailerMapping.getPrmId()).isEqualTo(DEFAULT_PRM_ID);
        assertThat(testRetailerMapping.getAgentCode()).isEqualTo(DEFAULT_AGENT_CODE);
        assertThat(testRetailerMapping.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testRetailerMapping.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testRetailerMapping.getRegion()).isEqualTo(DEFAULT_REGION);
        assertThat(testRetailerMapping.getRegistedOn()).isEqualTo(DEFAULT_REGISTED_ON);
        assertThat(testRetailerMapping.getMappedOn()).isEqualTo(DEFAULT_MAPPED_ON);
        assertThat(testRetailerMapping.getMappedTill()).isEqualTo(DEFAULT_MAPPED_TILL);
        assertThat(testRetailerMapping.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRetailerMapping.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRetailerMapping.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRetailerMapping.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRetailerMapping.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createRetailerMappingWithExistingId() throws Exception {
        // Create the RetailerMapping with an existing ID
        retailerMapping.setId(1L);
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        int databaseSizeBeforeCreate = retailerMappingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRetailerMappingMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRetailerMappings() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        // Get all the retailerMappingList
        restRetailerMappingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(retailerMapping.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].prmId").value(hasItem(DEFAULT_PRM_ID)))
            .andExpect(jsonPath("$.[*].agentCode").value(hasItem(DEFAULT_AGENT_CODE)))
            .andExpect(jsonPath("$.[*].pincode").value(hasItem(DEFAULT_PINCODE.intValue())))
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].region").value(hasItem(DEFAULT_REGION)))
            .andExpect(jsonPath("$.[*].registedOn").value(hasItem(DEFAULT_REGISTED_ON.toString())))
            .andExpect(jsonPath("$.[*].mappedOn").value(hasItem(DEFAULT_MAPPED_ON.toString())))
            .andExpect(jsonPath("$.[*].mappedTill").value(hasItem(DEFAULT_MAPPED_TILL.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getRetailerMapping() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        // Get the retailerMapping
        restRetailerMappingMockMvc
            .perform(get(ENTITY_API_URL_ID, retailerMapping.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(retailerMapping.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.prmId").value(DEFAULT_PRM_ID))
            .andExpect(jsonPath("$.agentCode").value(DEFAULT_AGENT_CODE))
            .andExpect(jsonPath("$.pincode").value(DEFAULT_PINCODE.intValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.region").value(DEFAULT_REGION))
            .andExpect(jsonPath("$.registedOn").value(DEFAULT_REGISTED_ON.toString()))
            .andExpect(jsonPath("$.mappedOn").value(DEFAULT_MAPPED_ON.toString()))
            .andExpect(jsonPath("$.mappedTill").value(DEFAULT_MAPPED_TILL.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingRetailerMapping() throws Exception {
        // Get the retailerMapping
        restRetailerMappingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRetailerMapping() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();

        // Update the retailerMapping
        RetailerMapping updatedRetailerMapping = retailerMappingRepository.findById(retailerMapping.getId()).get();
        // Disconnect from session so that the updates on updatedRetailerMapping are not directly saved in db
        em.detach(updatedRetailerMapping);
        updatedRetailerMapping
            .name(UPDATED_NAME)
            .prmId(UPDATED_PRM_ID)
            .agentCode(UPDATED_AGENT_CODE)
            .pincode(UPDATED_PINCODE)
            .state(UPDATED_STATE)
            .region(UPDATED_REGION)
            .registedOn(UPDATED_REGISTED_ON)
            .mappedOn(UPDATED_MAPPED_ON)
            .mappedTill(UPDATED_MAPPED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(updatedRetailerMapping);

        restRetailerMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, retailerMappingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isOk());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
        RetailerMapping testRetailerMapping = retailerMappingList.get(retailerMappingList.size() - 1);
        assertThat(testRetailerMapping.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRetailerMapping.getPrmId()).isEqualTo(UPDATED_PRM_ID);
        assertThat(testRetailerMapping.getAgentCode()).isEqualTo(UPDATED_AGENT_CODE);
        assertThat(testRetailerMapping.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testRetailerMapping.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testRetailerMapping.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testRetailerMapping.getRegistedOn()).isEqualTo(UPDATED_REGISTED_ON);
        assertThat(testRetailerMapping.getMappedOn()).isEqualTo(UPDATED_MAPPED_ON);
        assertThat(testRetailerMapping.getMappedTill()).isEqualTo(UPDATED_MAPPED_TILL);
        assertThat(testRetailerMapping.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRetailerMapping.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRetailerMapping.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRetailerMapping.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRetailerMapping.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, retailerMappingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRetailerMappingWithPatch() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();

        // Update the retailerMapping using partial update
        RetailerMapping partialUpdatedRetailerMapping = new RetailerMapping();
        partialUpdatedRetailerMapping.setId(retailerMapping.getId());

        partialUpdatedRetailerMapping
            .state(UPDATED_STATE)
            .region(UPDATED_REGION)
            .registedOn(UPDATED_REGISTED_ON)
            .mappedOn(UPDATED_MAPPED_ON);

        restRetailerMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRetailerMapping.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRetailerMapping))
            )
            .andExpect(status().isOk());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
        RetailerMapping testRetailerMapping = retailerMappingList.get(retailerMappingList.size() - 1);
        assertThat(testRetailerMapping.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testRetailerMapping.getPrmId()).isEqualTo(DEFAULT_PRM_ID);
        assertThat(testRetailerMapping.getAgentCode()).isEqualTo(DEFAULT_AGENT_CODE);
        assertThat(testRetailerMapping.getPincode()).isEqualTo(DEFAULT_PINCODE);
        assertThat(testRetailerMapping.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testRetailerMapping.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testRetailerMapping.getRegistedOn()).isEqualTo(UPDATED_REGISTED_ON);
        assertThat(testRetailerMapping.getMappedOn()).isEqualTo(UPDATED_MAPPED_ON);
        assertThat(testRetailerMapping.getMappedTill()).isEqualTo(DEFAULT_MAPPED_TILL);
        assertThat(testRetailerMapping.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testRetailerMapping.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testRetailerMapping.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testRetailerMapping.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testRetailerMapping.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateRetailerMappingWithPatch() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();

        // Update the retailerMapping using partial update
        RetailerMapping partialUpdatedRetailerMapping = new RetailerMapping();
        partialUpdatedRetailerMapping.setId(retailerMapping.getId());

        partialUpdatedRetailerMapping
            .name(UPDATED_NAME)
            .prmId(UPDATED_PRM_ID)
            .agentCode(UPDATED_AGENT_CODE)
            .pincode(UPDATED_PINCODE)
            .state(UPDATED_STATE)
            .region(UPDATED_REGION)
            .registedOn(UPDATED_REGISTED_ON)
            .mappedOn(UPDATED_MAPPED_ON)
            .mappedTill(UPDATED_MAPPED_TILL)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restRetailerMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRetailerMapping.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRetailerMapping))
            )
            .andExpect(status().isOk());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
        RetailerMapping testRetailerMapping = retailerMappingList.get(retailerMappingList.size() - 1);
        assertThat(testRetailerMapping.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testRetailerMapping.getPrmId()).isEqualTo(UPDATED_PRM_ID);
        assertThat(testRetailerMapping.getAgentCode()).isEqualTo(UPDATED_AGENT_CODE);
        assertThat(testRetailerMapping.getPincode()).isEqualTo(UPDATED_PINCODE);
        assertThat(testRetailerMapping.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testRetailerMapping.getRegion()).isEqualTo(UPDATED_REGION);
        assertThat(testRetailerMapping.getRegistedOn()).isEqualTo(UPDATED_REGISTED_ON);
        assertThat(testRetailerMapping.getMappedOn()).isEqualTo(UPDATED_MAPPED_ON);
        assertThat(testRetailerMapping.getMappedTill()).isEqualTo(UPDATED_MAPPED_TILL);
        assertThat(testRetailerMapping.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testRetailerMapping.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testRetailerMapping.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testRetailerMapping.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testRetailerMapping.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, retailerMappingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRetailerMapping() throws Exception {
        int databaseSizeBeforeUpdate = retailerMappingRepository.findAll().size();
        retailerMapping.setId(count.incrementAndGet());

        // Create the RetailerMapping
        RetailerMappingDTO retailerMappingDTO = retailerMappingMapper.toDto(retailerMapping);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRetailerMappingMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(retailerMappingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RetailerMapping in the database
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRetailerMapping() throws Exception {
        // Initialize the database
        retailerMappingRepository.saveAndFlush(retailerMapping);

        int databaseSizeBeforeDelete = retailerMappingRepository.findAll().size();

        // Delete the retailerMapping
        restRetailerMappingMockMvc
            .perform(delete(ENTITY_API_URL_ID, retailerMapping.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RetailerMapping> retailerMappingList = retailerMappingRepository.findAll();
        assertThat(retailerMappingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
