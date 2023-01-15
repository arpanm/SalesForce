package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.SalesTargetFilter;
import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.domain.enumeration.FilterJoinType;
import com.ril.digital.salesforce.repository.SalesTargetFilterRepository;
import com.ril.digital.salesforce.service.dto.SalesTargetFilterDTO;
import com.ril.digital.salesforce.service.mapper.SalesTargetFilterMapper;
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
 * Integration tests for the {@link SalesTargetFilterResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesTargetFilterResourceIT {

    private static final String DEFAULT_ARTICLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ID = "BBBBBBBBBB";

    private static final Category DEFAULT_CATEGORY = Category.CD;
    private static final Category UPDATED_CATEGORY = Category.IT;

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Float DEFAULT_ARTICLE_MIN_PRICE = 1F;
    private static final Float UPDATED_ARTICLE_MIN_PRICE = 2F;

    private static final Float DEFAULT_ARTICLE_MAX_PRICE = 1F;
    private static final Float UPDATED_ARTICLE_MAX_PRICE = 2F;

    private static final Long DEFAULT_PER_ORDER_ARTICLE_MIN_QTY = 1L;
    private static final Long UPDATED_PER_ORDER_ARTICLE_MIN_QTY = 2L;

    private static final Long DEFAULT_PER_ORDER_ARTICLE_MAX_QTY = 1L;
    private static final Long UPDATED_PER_ORDER_ARTICLE_MAX_QTY = 2L;

    private static final Long DEFAULT_PER_RETAILER_ARTICLE_MIN_QTY = 1L;
    private static final Long UPDATED_PER_RETAILER_ARTICLE_MIN_QTY = 2L;

    private static final Long DEFAULT_PER_RETAILER_ARTICLE_MAX_QTY = 1L;
    private static final Long UPDATED_PER_RETAILER_ARTICLE_MAX_QTY = 2L;

    private static final Long DEFAULT_PER_RETAILER_DAILY_ARTICLE_MIN_QTY = 1L;
    private static final Long UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY = 2L;

    private static final Long DEFAULT_PER_RETAILER_DAILY_ARTICLE_MAX_QTY = 1L;
    private static final Long UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY = 2L;

    private static final String DEFAULT_STATE = "AAAAAAAAAA";
    private static final String UPDATED_STATE = "BBBBBBBBBB";

    private static final FilterJoinType DEFAULT_JOIN = FilterJoinType.AND;
    private static final FilterJoinType UPDATED_JOIN = FilterJoinType.OR;

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

    private static final String ENTITY_API_URL = "/api/sales-target-filters";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesTargetFilterRepository salesTargetFilterRepository;

    @Autowired
    private SalesTargetFilterMapper salesTargetFilterMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesTargetFilterMockMvc;

    private SalesTargetFilter salesTargetFilter;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesTargetFilter createEntity(EntityManager em) {
        SalesTargetFilter salesTargetFilter = new SalesTargetFilter()
            .articleId(DEFAULT_ARTICLE_ID)
            .category(DEFAULT_CATEGORY)
            .brand(DEFAULT_BRAND)
            .model(DEFAULT_MODEL)
            .articleMinPrice(DEFAULT_ARTICLE_MIN_PRICE)
            .articleMaxPrice(DEFAULT_ARTICLE_MAX_PRICE)
            .perOrderArticleMinQty(DEFAULT_PER_ORDER_ARTICLE_MIN_QTY)
            .perOrderArticleMaxQty(DEFAULT_PER_ORDER_ARTICLE_MAX_QTY)
            .perRetailerArticleMinQty(DEFAULT_PER_RETAILER_ARTICLE_MIN_QTY)
            .perRetailerArticleMaxQty(DEFAULT_PER_RETAILER_ARTICLE_MAX_QTY)
            .perRetailerDailyArticleMinQty(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MIN_QTY)
            .perRetailerDailyArticleMaxQty(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MAX_QTY)
            .state(DEFAULT_STATE)
            .join(DEFAULT_JOIN)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return salesTargetFilter;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesTargetFilter createUpdatedEntity(EntityManager em) {
        SalesTargetFilter salesTargetFilter = new SalesTargetFilter()
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articleMinPrice(UPDATED_ARTICLE_MIN_PRICE)
            .articleMaxPrice(UPDATED_ARTICLE_MAX_PRICE)
            .perOrderArticleMinQty(UPDATED_PER_ORDER_ARTICLE_MIN_QTY)
            .perOrderArticleMaxQty(UPDATED_PER_ORDER_ARTICLE_MAX_QTY)
            .perRetailerArticleMinQty(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY)
            .perRetailerArticleMaxQty(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY)
            .perRetailerDailyArticleMinQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY)
            .perRetailerDailyArticleMaxQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY)
            .state(UPDATED_STATE)
            .join(UPDATED_JOIN)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return salesTargetFilter;
    }

    @BeforeEach
    public void initTest() {
        salesTargetFilter = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesTargetFilter() throws Exception {
        int databaseSizeBeforeCreate = salesTargetFilterRepository.findAll().size();
        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);
        restSalesTargetFilterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeCreate + 1);
        SalesTargetFilter testSalesTargetFilter = salesTargetFilterList.get(salesTargetFilterList.size() - 1);
        assertThat(testSalesTargetFilter.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testSalesTargetFilter.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testSalesTargetFilter.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testSalesTargetFilter.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testSalesTargetFilter.getArticleMinPrice()).isEqualTo(DEFAULT_ARTICLE_MIN_PRICE);
        assertThat(testSalesTargetFilter.getArticleMaxPrice()).isEqualTo(DEFAULT_ARTICLE_MAX_PRICE);
        assertThat(testSalesTargetFilter.getPerOrderArticleMinQty()).isEqualTo(DEFAULT_PER_ORDER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerOrderArticleMaxQty()).isEqualTo(DEFAULT_PER_ORDER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMinQty()).isEqualTo(DEFAULT_PER_RETAILER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMaxQty()).isEqualTo(DEFAULT_PER_RETAILER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMinQty()).isEqualTo(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMaxQty()).isEqualTo(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testSalesTargetFilter.getJoin()).isEqualTo(DEFAULT_JOIN);
        assertThat(testSalesTargetFilter.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSalesTargetFilter.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSalesTargetFilter.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testSalesTargetFilter.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSalesTargetFilter.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createSalesTargetFilterWithExistingId() throws Exception {
        // Create the SalesTargetFilter with an existing ID
        salesTargetFilter.setId(1L);
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        int databaseSizeBeforeCreate = salesTargetFilterRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesTargetFilterMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllSalesTargetFilters() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        // Get all the salesTargetFilterList
        restSalesTargetFilterMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesTargetFilter.getId().intValue())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].articleMinPrice").value(hasItem(DEFAULT_ARTICLE_MIN_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].articleMaxPrice").value(hasItem(DEFAULT_ARTICLE_MAX_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].perOrderArticleMinQty").value(hasItem(DEFAULT_PER_ORDER_ARTICLE_MIN_QTY.intValue())))
            .andExpect(jsonPath("$.[*].perOrderArticleMaxQty").value(hasItem(DEFAULT_PER_ORDER_ARTICLE_MAX_QTY.intValue())))
            .andExpect(jsonPath("$.[*].perRetailerArticleMinQty").value(hasItem(DEFAULT_PER_RETAILER_ARTICLE_MIN_QTY.intValue())))
            .andExpect(jsonPath("$.[*].perRetailerArticleMaxQty").value(hasItem(DEFAULT_PER_RETAILER_ARTICLE_MAX_QTY.intValue())))
            .andExpect(
                jsonPath("$.[*].perRetailerDailyArticleMinQty").value(hasItem(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MIN_QTY.intValue()))
            )
            .andExpect(
                jsonPath("$.[*].perRetailerDailyArticleMaxQty").value(hasItem(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MAX_QTY.intValue()))
            )
            .andExpect(jsonPath("$.[*].state").value(hasItem(DEFAULT_STATE)))
            .andExpect(jsonPath("$.[*].join").value(hasItem(DEFAULT_JOIN.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getSalesTargetFilter() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        // Get the salesTargetFilter
        restSalesTargetFilterMockMvc
            .perform(get(ENTITY_API_URL_ID, salesTargetFilter.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesTargetFilter.getId().intValue()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.articleMinPrice").value(DEFAULT_ARTICLE_MIN_PRICE.doubleValue()))
            .andExpect(jsonPath("$.articleMaxPrice").value(DEFAULT_ARTICLE_MAX_PRICE.doubleValue()))
            .andExpect(jsonPath("$.perOrderArticleMinQty").value(DEFAULT_PER_ORDER_ARTICLE_MIN_QTY.intValue()))
            .andExpect(jsonPath("$.perOrderArticleMaxQty").value(DEFAULT_PER_ORDER_ARTICLE_MAX_QTY.intValue()))
            .andExpect(jsonPath("$.perRetailerArticleMinQty").value(DEFAULT_PER_RETAILER_ARTICLE_MIN_QTY.intValue()))
            .andExpect(jsonPath("$.perRetailerArticleMaxQty").value(DEFAULT_PER_RETAILER_ARTICLE_MAX_QTY.intValue()))
            .andExpect(jsonPath("$.perRetailerDailyArticleMinQty").value(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MIN_QTY.intValue()))
            .andExpect(jsonPath("$.perRetailerDailyArticleMaxQty").value(DEFAULT_PER_RETAILER_DAILY_ARTICLE_MAX_QTY.intValue()))
            .andExpect(jsonPath("$.state").value(DEFAULT_STATE))
            .andExpect(jsonPath("$.join").value(DEFAULT_JOIN.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingSalesTargetFilter() throws Exception {
        // Get the salesTargetFilter
        restSalesTargetFilterMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesTargetFilter() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();

        // Update the salesTargetFilter
        SalesTargetFilter updatedSalesTargetFilter = salesTargetFilterRepository.findById(salesTargetFilter.getId()).get();
        // Disconnect from session so that the updates on updatedSalesTargetFilter are not directly saved in db
        em.detach(updatedSalesTargetFilter);
        updatedSalesTargetFilter
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articleMinPrice(UPDATED_ARTICLE_MIN_PRICE)
            .articleMaxPrice(UPDATED_ARTICLE_MAX_PRICE)
            .perOrderArticleMinQty(UPDATED_PER_ORDER_ARTICLE_MIN_QTY)
            .perOrderArticleMaxQty(UPDATED_PER_ORDER_ARTICLE_MAX_QTY)
            .perRetailerArticleMinQty(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY)
            .perRetailerArticleMaxQty(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY)
            .perRetailerDailyArticleMinQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY)
            .perRetailerDailyArticleMaxQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY)
            .state(UPDATED_STATE)
            .join(UPDATED_JOIN)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(updatedSalesTargetFilter);

        restSalesTargetFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesTargetFilterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
        SalesTargetFilter testSalesTargetFilter = salesTargetFilterList.get(salesTargetFilterList.size() - 1);
        assertThat(testSalesTargetFilter.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testSalesTargetFilter.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSalesTargetFilter.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testSalesTargetFilter.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testSalesTargetFilter.getArticleMinPrice()).isEqualTo(UPDATED_ARTICLE_MIN_PRICE);
        assertThat(testSalesTargetFilter.getArticleMaxPrice()).isEqualTo(UPDATED_ARTICLE_MAX_PRICE);
        assertThat(testSalesTargetFilter.getPerOrderArticleMinQty()).isEqualTo(UPDATED_PER_ORDER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerOrderArticleMaxQty()).isEqualTo(UPDATED_PER_ORDER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testSalesTargetFilter.getJoin()).isEqualTo(UPDATED_JOIN);
        assertThat(testSalesTargetFilter.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesTargetFilter.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesTargetFilter.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesTargetFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTargetFilter.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesTargetFilterDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesTargetFilterWithPatch() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();

        // Update the salesTargetFilter using partial update
        SalesTargetFilter partialUpdatedSalesTargetFilter = new SalesTargetFilter();
        partialUpdatedSalesTargetFilter.setId(salesTargetFilter.getId());

        partialUpdatedSalesTargetFilter
            .brand(UPDATED_BRAND)
            .perRetailerArticleMinQty(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY)
            .perRetailerArticleMaxQty(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY)
            .perRetailerDailyArticleMinQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY)
            .perRetailerDailyArticleMaxQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY)
            .isActive(UPDATED_IS_ACTIVE)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY);

        restSalesTargetFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesTargetFilter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesTargetFilter))
            )
            .andExpect(status().isOk());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
        SalesTargetFilter testSalesTargetFilter = salesTargetFilterList.get(salesTargetFilterList.size() - 1);
        assertThat(testSalesTargetFilter.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testSalesTargetFilter.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testSalesTargetFilter.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testSalesTargetFilter.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testSalesTargetFilter.getArticleMinPrice()).isEqualTo(DEFAULT_ARTICLE_MIN_PRICE);
        assertThat(testSalesTargetFilter.getArticleMaxPrice()).isEqualTo(DEFAULT_ARTICLE_MAX_PRICE);
        assertThat(testSalesTargetFilter.getPerOrderArticleMinQty()).isEqualTo(DEFAULT_PER_ORDER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerOrderArticleMaxQty()).isEqualTo(DEFAULT_PER_ORDER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getState()).isEqualTo(DEFAULT_STATE);
        assertThat(testSalesTargetFilter.getJoin()).isEqualTo(DEFAULT_JOIN);
        assertThat(testSalesTargetFilter.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesTargetFilter.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSalesTargetFilter.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesTargetFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTargetFilter.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateSalesTargetFilterWithPatch() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();

        // Update the salesTargetFilter using partial update
        SalesTargetFilter partialUpdatedSalesTargetFilter = new SalesTargetFilter();
        partialUpdatedSalesTargetFilter.setId(salesTargetFilter.getId());

        partialUpdatedSalesTargetFilter
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articleMinPrice(UPDATED_ARTICLE_MIN_PRICE)
            .articleMaxPrice(UPDATED_ARTICLE_MAX_PRICE)
            .perOrderArticleMinQty(UPDATED_PER_ORDER_ARTICLE_MIN_QTY)
            .perOrderArticleMaxQty(UPDATED_PER_ORDER_ARTICLE_MAX_QTY)
            .perRetailerArticleMinQty(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY)
            .perRetailerArticleMaxQty(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY)
            .perRetailerDailyArticleMinQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY)
            .perRetailerDailyArticleMaxQty(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY)
            .state(UPDATED_STATE)
            .join(UPDATED_JOIN)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restSalesTargetFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesTargetFilter.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesTargetFilter))
            )
            .andExpect(status().isOk());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
        SalesTargetFilter testSalesTargetFilter = salesTargetFilterList.get(salesTargetFilterList.size() - 1);
        assertThat(testSalesTargetFilter.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testSalesTargetFilter.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testSalesTargetFilter.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testSalesTargetFilter.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testSalesTargetFilter.getArticleMinPrice()).isEqualTo(UPDATED_ARTICLE_MIN_PRICE);
        assertThat(testSalesTargetFilter.getArticleMaxPrice()).isEqualTo(UPDATED_ARTICLE_MAX_PRICE);
        assertThat(testSalesTargetFilter.getPerOrderArticleMinQty()).isEqualTo(UPDATED_PER_ORDER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerOrderArticleMaxQty()).isEqualTo(UPDATED_PER_ORDER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMinQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MIN_QTY);
        assertThat(testSalesTargetFilter.getPerRetailerDailyArticleMaxQty()).isEqualTo(UPDATED_PER_RETAILER_DAILY_ARTICLE_MAX_QTY);
        assertThat(testSalesTargetFilter.getState()).isEqualTo(UPDATED_STATE);
        assertThat(testSalesTargetFilter.getJoin()).isEqualTo(UPDATED_JOIN);
        assertThat(testSalesTargetFilter.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesTargetFilter.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesTargetFilter.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesTargetFilter.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesTargetFilter.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesTargetFilterDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesTargetFilter() throws Exception {
        int databaseSizeBeforeUpdate = salesTargetFilterRepository.findAll().size();
        salesTargetFilter.setId(count.incrementAndGet());

        // Create the SalesTargetFilter
        SalesTargetFilterDTO salesTargetFilterDTO = salesTargetFilterMapper.toDto(salesTargetFilter);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesTargetFilterMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesTargetFilterDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesTargetFilter in the database
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesTargetFilter() throws Exception {
        // Initialize the database
        salesTargetFilterRepository.saveAndFlush(salesTargetFilter);

        int databaseSizeBeforeDelete = salesTargetFilterRepository.findAll().size();

        // Delete the salesTargetFilter
        restSalesTargetFilterMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesTargetFilter.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesTargetFilter> salesTargetFilterList = salesTargetFilterRepository.findAll();
        assertThat(salesTargetFilterList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
