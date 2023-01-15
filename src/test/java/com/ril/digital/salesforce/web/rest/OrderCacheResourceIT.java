package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.OrderCache;
import com.ril.digital.salesforce.domain.enumeration.Category;
import com.ril.digital.salesforce.domain.enumeration.OrderStatus;
import com.ril.digital.salesforce.repository.OrderCacheRepository;
import com.ril.digital.salesforce.service.dto.OrderCacheDTO;
import com.ril.digital.salesforce.service.mapper.OrderCacheMapper;
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
 * Integration tests for the {@link OrderCacheResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class OrderCacheResourceIT {

    private static final String DEFAULT_ORDER_ID = "AAAAAAAAAA";
    private static final String UPDATED_ORDER_ID = "BBBBBBBBBB";

    private static final OrderStatus DEFAULT_ORDER_STATUS = OrderStatus.Open;
    private static final OrderStatus UPDATED_ORDER_STATUS = OrderStatus.Cancelled;

    private static final LocalDate DEFAULT_ORDER_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ORDER_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_ARTICLE_ID = "AAAAAAAAAA";
    private static final String UPDATED_ARTICLE_ID = "BBBBBBBBBB";

    private static final Category DEFAULT_CATEGORY = Category.CD;
    private static final Category UPDATED_CATEGORY = Category.IT;

    private static final String DEFAULT_BRAND = "AAAAAAAAAA";
    private static final String UPDATED_BRAND = "BBBBBBBBBB";

    private static final String DEFAULT_MODEL = "AAAAAAAAAA";
    private static final String UPDATED_MODEL = "BBBBBBBBBB";

    private static final Float DEFAULT_ARTICLE_PRICE = 1F;
    private static final Float UPDATED_ARTICLE_PRICE = 2F;

    private static final Long DEFAULT_ARTICLE_QTY = 1L;
    private static final Long UPDATED_ARTICLE_QTY = 2L;

    private static final String DEFAULT_RETAILER_ID = "AAAAAAAAAA";
    private static final String UPDATED_RETAILER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_RETAILER_STATE = "AAAAAAAAAA";
    private static final String UPDATED_RETAILER_STATE = "BBBBBBBBBB";

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

    private static final String ENTITY_API_URL = "/api/order-caches";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private OrderCacheRepository orderCacheRepository;

    @Autowired
    private OrderCacheMapper orderCacheMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restOrderCacheMockMvc;

    private OrderCache orderCache;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderCache createEntity(EntityManager em) {
        OrderCache orderCache = new OrderCache()
            .orderId(DEFAULT_ORDER_ID)
            .orderStatus(DEFAULT_ORDER_STATUS)
            .orderDate(DEFAULT_ORDER_DATE)
            .articleId(DEFAULT_ARTICLE_ID)
            .category(DEFAULT_CATEGORY)
            .brand(DEFAULT_BRAND)
            .model(DEFAULT_MODEL)
            .articlePrice(DEFAULT_ARTICLE_PRICE)
            .articleQty(DEFAULT_ARTICLE_QTY)
            .retailerId(DEFAULT_RETAILER_ID)
            .retailerState(DEFAULT_RETAILER_STATE)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return orderCache;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static OrderCache createUpdatedEntity(EntityManager em) {
        OrderCache orderCache = new OrderCache()
            .orderId(UPDATED_ORDER_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderDate(UPDATED_ORDER_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articlePrice(UPDATED_ARTICLE_PRICE)
            .articleQty(UPDATED_ARTICLE_QTY)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return orderCache;
    }

    @BeforeEach
    public void initTest() {
        orderCache = createEntity(em);
    }

    @Test
    @Transactional
    void createOrderCache() throws Exception {
        int databaseSizeBeforeCreate = orderCacheRepository.findAll().size();
        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);
        restOrderCacheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderCacheDTO)))
            .andExpect(status().isCreated());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeCreate + 1);
        OrderCache testOrderCache = orderCacheList.get(orderCacheList.size() - 1);
        assertThat(testOrderCache.getOrderId()).isEqualTo(DEFAULT_ORDER_ID);
        assertThat(testOrderCache.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testOrderCache.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testOrderCache.getArticleId()).isEqualTo(DEFAULT_ARTICLE_ID);
        assertThat(testOrderCache.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testOrderCache.getBrand()).isEqualTo(DEFAULT_BRAND);
        assertThat(testOrderCache.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testOrderCache.getArticlePrice()).isEqualTo(DEFAULT_ARTICLE_PRICE);
        assertThat(testOrderCache.getArticleQty()).isEqualTo(DEFAULT_ARTICLE_QTY);
        assertThat(testOrderCache.getRetailerId()).isEqualTo(DEFAULT_RETAILER_ID);
        assertThat(testOrderCache.getRetailerState()).isEqualTo(DEFAULT_RETAILER_STATE);
        assertThat(testOrderCache.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderCache.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testOrderCache.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testOrderCache.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testOrderCache.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createOrderCacheWithExistingId() throws Exception {
        // Create the OrderCache with an existing ID
        orderCache.setId(1L);
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        int databaseSizeBeforeCreate = orderCacheRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restOrderCacheMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderCacheDTO)))
            .andExpect(status().isBadRequest());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllOrderCaches() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        // Get all the orderCacheList
        restOrderCacheMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(orderCache.getId().intValue())))
            .andExpect(jsonPath("$.[*].orderId").value(hasItem(DEFAULT_ORDER_ID)))
            .andExpect(jsonPath("$.[*].orderStatus").value(hasItem(DEFAULT_ORDER_STATUS.toString())))
            .andExpect(jsonPath("$.[*].orderDate").value(hasItem(DEFAULT_ORDER_DATE.toString())))
            .andExpect(jsonPath("$.[*].articleId").value(hasItem(DEFAULT_ARTICLE_ID)))
            .andExpect(jsonPath("$.[*].category").value(hasItem(DEFAULT_CATEGORY.toString())))
            .andExpect(jsonPath("$.[*].brand").value(hasItem(DEFAULT_BRAND)))
            .andExpect(jsonPath("$.[*].model").value(hasItem(DEFAULT_MODEL)))
            .andExpect(jsonPath("$.[*].articlePrice").value(hasItem(DEFAULT_ARTICLE_PRICE.doubleValue())))
            .andExpect(jsonPath("$.[*].articleQty").value(hasItem(DEFAULT_ARTICLE_QTY.intValue())))
            .andExpect(jsonPath("$.[*].retailerId").value(hasItem(DEFAULT_RETAILER_ID)))
            .andExpect(jsonPath("$.[*].retailerState").value(hasItem(DEFAULT_RETAILER_STATE)))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getOrderCache() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        // Get the orderCache
        restOrderCacheMockMvc
            .perform(get(ENTITY_API_URL_ID, orderCache.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(orderCache.getId().intValue()))
            .andExpect(jsonPath("$.orderId").value(DEFAULT_ORDER_ID))
            .andExpect(jsonPath("$.orderStatus").value(DEFAULT_ORDER_STATUS.toString()))
            .andExpect(jsonPath("$.orderDate").value(DEFAULT_ORDER_DATE.toString()))
            .andExpect(jsonPath("$.articleId").value(DEFAULT_ARTICLE_ID))
            .andExpect(jsonPath("$.category").value(DEFAULT_CATEGORY.toString()))
            .andExpect(jsonPath("$.brand").value(DEFAULT_BRAND))
            .andExpect(jsonPath("$.model").value(DEFAULT_MODEL))
            .andExpect(jsonPath("$.articlePrice").value(DEFAULT_ARTICLE_PRICE.doubleValue()))
            .andExpect(jsonPath("$.articleQty").value(DEFAULT_ARTICLE_QTY.intValue()))
            .andExpect(jsonPath("$.retailerId").value(DEFAULT_RETAILER_ID))
            .andExpect(jsonPath("$.retailerState").value(DEFAULT_RETAILER_STATE))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingOrderCache() throws Exception {
        // Get the orderCache
        restOrderCacheMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingOrderCache() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();

        // Update the orderCache
        OrderCache updatedOrderCache = orderCacheRepository.findById(orderCache.getId()).get();
        // Disconnect from session so that the updates on updatedOrderCache are not directly saved in db
        em.detach(updatedOrderCache);
        updatedOrderCache
            .orderId(UPDATED_ORDER_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderDate(UPDATED_ORDER_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articlePrice(UPDATED_ARTICLE_PRICE)
            .articleQty(UPDATED_ARTICLE_QTY)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(updatedOrderCache);

        restOrderCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderCacheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isOk());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
        OrderCache testOrderCache = orderCacheList.get(orderCacheList.size() - 1);
        assertThat(testOrderCache.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderCache.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testOrderCache.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testOrderCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testOrderCache.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testOrderCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testOrderCache.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testOrderCache.getArticlePrice()).isEqualTo(UPDATED_ARTICLE_PRICE);
        assertThat(testOrderCache.getArticleQty()).isEqualTo(UPDATED_ARTICLE_QTY);
        assertThat(testOrderCache.getRetailerId()).isEqualTo(UPDATED_RETAILER_ID);
        assertThat(testOrderCache.getRetailerState()).isEqualTo(UPDATED_RETAILER_STATE);
        assertThat(testOrderCache.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOrderCache.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testOrderCache.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, orderCacheDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(orderCacheDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateOrderCacheWithPatch() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();

        // Update the orderCache using partial update
        OrderCache partialUpdatedOrderCache = new OrderCache();
        partialUpdatedOrderCache.setId(orderCache.getId());

        partialUpdatedOrderCache
            .orderId(UPDATED_ORDER_ID)
            .articleId(UPDATED_ARTICLE_ID)
            .brand(UPDATED_BRAND)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restOrderCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderCache.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderCache))
            )
            .andExpect(status().isOk());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
        OrderCache testOrderCache = orderCacheList.get(orderCacheList.size() - 1);
        assertThat(testOrderCache.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderCache.getOrderStatus()).isEqualTo(DEFAULT_ORDER_STATUS);
        assertThat(testOrderCache.getOrderDate()).isEqualTo(DEFAULT_ORDER_DATE);
        assertThat(testOrderCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testOrderCache.getCategory()).isEqualTo(DEFAULT_CATEGORY);
        assertThat(testOrderCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testOrderCache.getModel()).isEqualTo(DEFAULT_MODEL);
        assertThat(testOrderCache.getArticlePrice()).isEqualTo(DEFAULT_ARTICLE_PRICE);
        assertThat(testOrderCache.getArticleQty()).isEqualTo(DEFAULT_ARTICLE_QTY);
        assertThat(testOrderCache.getRetailerId()).isEqualTo(DEFAULT_RETAILER_ID);
        assertThat(testOrderCache.getRetailerState()).isEqualTo(DEFAULT_RETAILER_STATE);
        assertThat(testOrderCache.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testOrderCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOrderCache.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testOrderCache.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateOrderCacheWithPatch() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();

        // Update the orderCache using partial update
        OrderCache partialUpdatedOrderCache = new OrderCache();
        partialUpdatedOrderCache.setId(orderCache.getId());

        partialUpdatedOrderCache
            .orderId(UPDATED_ORDER_ID)
            .orderStatus(UPDATED_ORDER_STATUS)
            .orderDate(UPDATED_ORDER_DATE)
            .articleId(UPDATED_ARTICLE_ID)
            .category(UPDATED_CATEGORY)
            .brand(UPDATED_BRAND)
            .model(UPDATED_MODEL)
            .articlePrice(UPDATED_ARTICLE_PRICE)
            .articleQty(UPDATED_ARTICLE_QTY)
            .retailerId(UPDATED_RETAILER_ID)
            .retailerState(UPDATED_RETAILER_STATE)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restOrderCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedOrderCache.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedOrderCache))
            )
            .andExpect(status().isOk());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
        OrderCache testOrderCache = orderCacheList.get(orderCacheList.size() - 1);
        assertThat(testOrderCache.getOrderId()).isEqualTo(UPDATED_ORDER_ID);
        assertThat(testOrderCache.getOrderStatus()).isEqualTo(UPDATED_ORDER_STATUS);
        assertThat(testOrderCache.getOrderDate()).isEqualTo(UPDATED_ORDER_DATE);
        assertThat(testOrderCache.getArticleId()).isEqualTo(UPDATED_ARTICLE_ID);
        assertThat(testOrderCache.getCategory()).isEqualTo(UPDATED_CATEGORY);
        assertThat(testOrderCache.getBrand()).isEqualTo(UPDATED_BRAND);
        assertThat(testOrderCache.getModel()).isEqualTo(UPDATED_MODEL);
        assertThat(testOrderCache.getArticlePrice()).isEqualTo(UPDATED_ARTICLE_PRICE);
        assertThat(testOrderCache.getArticleQty()).isEqualTo(UPDATED_ARTICLE_QTY);
        assertThat(testOrderCache.getRetailerId()).isEqualTo(UPDATED_RETAILER_ID);
        assertThat(testOrderCache.getRetailerState()).isEqualTo(UPDATED_RETAILER_STATE);
        assertThat(testOrderCache.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testOrderCache.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testOrderCache.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testOrderCache.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testOrderCache.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, orderCacheDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamOrderCache() throws Exception {
        int databaseSizeBeforeUpdate = orderCacheRepository.findAll().size();
        orderCache.setId(count.incrementAndGet());

        // Create the OrderCache
        OrderCacheDTO orderCacheDTO = orderCacheMapper.toDto(orderCache);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restOrderCacheMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(orderCacheDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the OrderCache in the database
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteOrderCache() throws Exception {
        // Initialize the database
        orderCacheRepository.saveAndFlush(orderCache);

        int databaseSizeBeforeDelete = orderCacheRepository.findAll().size();

        // Delete the orderCache
        restOrderCacheMockMvc
            .perform(delete(ENTITY_API_URL_ID, orderCache.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<OrderCache> orderCacheList = orderCacheRepository.findAll();
        assertThat(orderCacheList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
