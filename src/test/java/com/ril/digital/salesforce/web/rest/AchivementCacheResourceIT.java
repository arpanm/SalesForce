package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.AchivementCache;
import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.repository.AchivementCacheRepository;
import com.ril.digital.salesforce.service.dto.AchivementCacheDTO;
import com.ril.digital.salesforce.service.mapper.AchivementCacheMapper;
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
 * Integration tests for the {@link AchivementCacheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class AchivementCacheResourceIT {

    private static final LocalDate DEFAULT_START_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_START_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_END_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_END_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ARTICLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ID = "BBBBBBBBBB";

    private static final Category DEFAULT_CATEGORY = Category.CD;
    private static final Category UPDATED_CATEGORY = Category.IT;

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final String DEFAULT_RETAILER_ID = "AAAAAAAAAA";
    private static final String UPDATED_RETAILER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RETAILER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_RETAILER_STATE = "BBBBBBBBBB";

    private static final Long DEFAULT_ORDER_QTY = 1L;
    private static final Long UPDATED_ORDER_QTY = 2L;

    private static final Float DEFAULT_TOTAL_ORDER_VALUE = 1F;
    private static final Float UPDATED_TOTAL_ORDER_VALUE = 2F;

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

    private static final String ENTITY_API_URL = "/api/achivement-caches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AchivementCacheRepository achivementCacheRepository;

    @Autowired
    private AchivementCacheMapper achivementCacheMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restAchivementCacheMockMvc;

    private AchivementCache achivementCache;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AchivementCache createEntity(EntityManager em) {
        AchivementCache achivementCache = new AchivementCache()
            .startDate(DEFAULT_START_DATE)
            .endDate(DEFAULT_END_DATE)
            .articleId(DEFAULT_ARTICLE_ID)
            .category(DEFAULT_CATEGORY)
            .brand(DEFAULT_BRAND)
            .model(DEFAULT_MODEL)
            .retailerId(DEFAULT_RETAILER_ID)
            .retailerState(DEFAULT_RETAILER_STATE)
            .orderQty(DEFAULT_ORDER_QTY)
            .totalOrderValue(DEFAULT_TOTAL_ORDER_VALUE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return achivementCache;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AchivementCache createUpdatedEntity(EntityManager em) {
        AchivementCache achivementCache = new AchivementCache()
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .orderQty(UPDATED_ORDER_QTY)
            .totalOrderValue(UPDATED_TOTAL_ORDER_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return achivementCache;
    }

    @BeforeEach
    public void initTest() {
        achivementCache = createEntity(em);
    }

    @Test
    @Transactional
    void createAchivementCache() throws Exception {
        int databaseSizeBeforeCreate = achivementCacheRepository.findAll().size();
        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);
        restAchivementCacheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isCreated());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeCreate + 1);
        AchivementCache testAchivementCache = achivementCacheList.get(achivementCacheList.size() - 1);
        assertThat(testAchivementCache.getStartDate()).isEqualTo(DEFAULT_START_DATE);
        assertThat(testAchivementCache.getEndDate()).isEqualTo(DEFAULT_END_DATE);
        assertThat(testAchivementCache.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testAchivementCache.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testAchivementCache.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testAchivementCache.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testAchivementCache.getRetailerId()).isEqualTo(DEFAULT_RETAILER_ID);
        assertThat(testAchivementCache.getRetailerState()).isEqualTo(DEFAULT_RETAILER_STATE);
        assertThat(testAchivementCache.getOrderQty()).isEqualTo(DEFAULT_ORDER_QTY);
        assertThat(testAchivementCache.getTotalOrderValue()).isEqualTo(DEFAULT_TOTAL_ORDER_VALUE);
        assertThat(testAchivementCache.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAchivementCache.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAchivementCache.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAchivementCache.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAchivementCache.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createAchivementCacheWithExistingId() throws Exception {
        // Create the AchivementCache with an existing ID
        achivementCache.setId(1L);
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        int databaseSizeBeforeCreate = achivementCacheRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restAchivementCacheMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllAchivementCaches() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        // Get all the achivementCacheList
        restAchivementCacheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(achivementCache.getId().intValue())))
            .andExpect(jsonPath("$.[*].startDate").value(hasItem(DEFAULT_START_DATE.toString())))
            .andExpect(jsonPath("$.[*].endDate").value(hasItem(DEFAULT_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].retailerId").value(hasItem(DEFAULT_RETAILER_ID)))
            .andExpect(jsonPath("$.[*].retailerState").value(hasItem(DEFAULT_RETAILER_STATE)))
            .andExpect(jsonPath("$.[*].orderQty").value(hasItem(DEFAULT_ORDER_QTY.intValue())))
            .andExpect(jsonPath("$.[*].totalOrderValue").value(hasItem(DEFAULT_TOTAL_ORDER_VALUE.doubleValue())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getAchivementCache() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        // Get the achivementCache
        restAchivementCacheMockMvc
            .perform(get(ENTITY_API_URL_ID, achivementCache.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(achivementCache.getId().intValue()))
            .andExpect(jsonPath("$.startDate").value(DEFAULT_START_DATE.toString()))
            .andExpect(jsonPath("$.endDate").value(DEFAULT_END_DATE.toString()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.retailerId").value(DEFAULT_RETAILER_ID))
            .andExpect(jsonPath("$.retailerState").value(DEFAULT_RETAILER_STATE))
            .andExpect(jsonPath("$.orderQty").value(DEFAULT_ORDER_QTY.intValue()))
            .andExpect(jsonPath("$.totalOrderValue").value(DEFAULT_TOTAL_ORDER_VALUE.doubleValue()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingAchivementCache() throws Exception {
        // Get the achivementCache
        restAchivementCacheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingAchivementCache() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();

        // Update the achivementCache
        AchivementCache updatedAchivementCache = achivementCacheRepository.findById(achivementCache.getId()).get();
        // Disconnect from session so that the updates on updatedAchivementCache are not directly saved in db
        em.detach(updatedAchivementCache);
        updatedAchivementCache
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .orderQty(UPDATED_ORDER_QTY)
            .totalOrderValue(UPDATED_TOTAL_ORDER_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(updatedAchivementCache);

        restAchivementCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, achivementCacheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isOk());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
        AchivementCache testAchivementCache = achivementCacheList.get(achivementCacheList.size() - 1);
        assertThat(testAchivementCache.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAchivementCache.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testAchivementCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testAchivementCache.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testAchivementCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testAchivementCache.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testAchivementCache.getRetailerId()).isEqualTo(UPDATED_RETAILER_ID);
        assertThat(testAchivementCache.getRetailerState()).isEqualTo(UPDATED_RETAILER_STATE);
        assertThat(testAchivementCache.getOrderQty()).isEqualTo(UPDATED_ORDER_QTY);
        assertThat(testAchivementCache.getTotalOrderValue()).isEqualTo(UPDATED_TOTAL_ORDER_VALUE);
        assertThat(testAchivementCache.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAchivementCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAchivementCache.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAchivementCache.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAchivementCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, achivementCacheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateAchivementCacheWithPatch() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();

        // Update the achivementCache using partial update
        AchivementCache partialUpdatedAchivementCache = new AchivementCache();
        partialUpdatedAchivementCache.setId(achivementCache.getId());

        partialUpdatedAchivementCache
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        restAchivementCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAchivementCache.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAchivementCache))
            )
            .andExpect(status().isOk());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
        AchivementCache testAchivementCache = achivementCacheList.get(achivementCacheList.size() - 1);
        assertThat(testAchivementCache.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAchivementCache.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testAchivementCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testAchivementCache.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testAchivementCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testAchivementCache.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testAchivementCache.getRetailerId()).isEqualTo(DEFAULT_RETAILER_ID);
        assertThat(testAchivementCache.getRetailerState()).isEqualTo(DEFAULT_RETAILER_STATE);
        assertThat(testAchivementCache.getOrderQty()).isEqualTo(DEFAULT_ORDER_QTY);
        assertThat(testAchivementCache.getTotalOrderValue()).isEqualTo(DEFAULT_TOTAL_ORDER_VALUE);
        assertThat(testAchivementCache.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testAchivementCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAchivementCache.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAchivementCache.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAchivementCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateAchivementCacheWithPatch() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();

        // Update the achivementCache using partial update
        AchivementCache partialUpdatedAchivementCache = new AchivementCache();
        partialUpdatedAchivementCache.setId(achivementCache.getId());

        partialUpdatedAchivementCache
            .startDate(UPDATED_START_DATE)
            .endDate(UPDATED_END_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .orderQty(UPDATED_ORDER_QTY)
            .totalOrderValue(UPDATED_TOTAL_ORDER_VALUE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restAchivementCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedAchivementCache.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedAchivementCache))
            )
            .andExpect(status().isOk());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
        AchivementCache testAchivementCache = achivementCacheList.get(achivementCacheList.size() - 1);
        assertThat(testAchivementCache.getStartDate()).isEqualTo(UPDATED_START_DATE);
        assertThat(testAchivementCache.getEndDate()).isEqualTo(UPDATED_END_DATE);
        assertThat(testAchivementCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testAchivementCache.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testAchivementCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testAchivementCache.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testAchivementCache.getRetailerId()).isEqualTo(UPDATED_RETAILER_ID);
        assertThat(testAchivementCache.getRetailerState()).isEqualTo(UPDATED_RETAILER_STATE);
        assertThat(testAchivementCache.getOrderQty()).isEqualTo(UPDATED_ORDER_QTY);
        assertThat(testAchivementCache.getTotalOrderValue()).isEqualTo(UPDATED_TOTAL_ORDER_VALUE);
        assertThat(testAchivementCache.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testAchivementCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAchivementCache.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAchivementCache.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAchivementCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, achivementCacheDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamAchivementCache() throws Exception {
        int databaseSizeBeforeUpdate = achivementCacheRepository.findAll().size();
        achivementCache.setId(count.incrementAndGet());

        // Create the AchivementCache
        AchivementCacheDTO achivementCacheDTO = achivementCacheMapper.toDto(achivementCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restAchivementCacheMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(achivementCacheDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the AchivementCache in the database
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteAchivementCache() throws Exception {
        // Initialize the database
        achivementCacheRepository.saveAndFlush(achivementCache);

        int databaseSizeBeforeDelete = achivementCacheRepository.findAll().size();

        // Delete the achivementCache
        restAchivementCacheMockMvc
            .perform(delete(ENTITY_API_URL_ID, achivementCache.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<AchivementCache> achivementCacheList = achivementCacheRepository.findAll();
        assertThat(achivementCacheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
