package com.ril.digital.salesforce.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.ril.digital.salesforce.IntegrationTest;
import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.domain.enumeration.Role;
import com.ril.digital.salesforce.repository.SalesForceUserRepository;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import com.ril.digital.salesforce.service.mapper.SalesForceUserMapper;
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
 * Integration tests for the {@link SalesForceUserResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class SalesForceUserResourceIT {

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "aPl<d=@ril.sNp;o";
    private static final String UPDATED_EMAIL = "NoE@ril.[dOm";

    private static final Long DEFAULT_PHONE = 1000000000L;
    private static final Long UPDATED_PHONE = 1000000001L;

    private static final Role DEFAULT_USER_ROLE = Role.SuperAdmin;
    private static final Role UPDATED_USER_ROLE = Role.HRAdmin;

    private static final LocalDate DEFAULT_DATE_OF_JOINING = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_JOINING = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_OF_EXIT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_OF_EXIT = LocalDate.now(ZoneId.systemDefault());

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

    private static final String ENTITY_API_URL = "/api/sales-force-users";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private SalesForceUserRepository salesForceUserRepository;

    @Autowired
    private SalesForceUserMapper salesForceUserMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restSalesForceUserMockMvc;

    private SalesForceUser salesForceUser;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesForceUser createEntity(EntityManager em) {
        SalesForceUser salesForceUser = new SalesForceUser()
            .name(DEFAULT_NAME)
            .email(DEFAULT_EMAIL)
            .phone(DEFAULT_PHONE)
            .userRole(DEFAULT_USER_ROLE)
            .dateOfJoining(DEFAULT_DATE_OF_JOINING)
            .dateOfExit(DEFAULT_DATE_OF_EXIT)
            .isActive(DEFAULT_IS_ACTIVE)
            .createdAt(DEFAULT_CREATED_AT)
            .updatedAt(DEFAULT_UPDATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedBy(DEFAULT_UPDATED_BY);
        return salesForceUser;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SalesForceUser createUpdatedEntity(EntityManager em) {
        SalesForceUser salesForceUser = new SalesForceUser()
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .userRole(UPDATED_USER_ROLE)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfExit(UPDATED_DATE_OF_EXIT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        return salesForceUser;
    }

    @BeforeEach
    public void initTest() {
        salesForceUser = createEntity(em);
    }

    @Test
    @Transactional
    void createSalesForceUser() throws Exception {
        int databaseSizeBeforeCreate = salesForceUserRepository.findAll().size();
        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);
        restSalesForceUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isCreated());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeCreate + 1);
        SalesForceUser testSalesForceUser = salesForceUserList.get(salesForceUserList.size() - 1);
        assertThat(testSalesForceUser.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testSalesForceUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSalesForceUser.getPhone()).isEqualTo(DEFAULT_PHONE);
        assertThat(testSalesForceUser.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testSalesForceUser.getDateOfJoining()).isEqualTo(DEFAULT_DATE_OF_JOINING);
        assertThat(testSalesForceUser.getDateOfExit()).isEqualTo(DEFAULT_DATE_OF_EXIT);
        assertThat(testSalesForceUser.getIsActive()).isEqualTo(DEFAULT_IS_ACTIVE);
        assertThat(testSalesForceUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSalesForceUser.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testSalesForceUser.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testSalesForceUser.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    @Transactional
    void createSalesForceUserWithExistingId() throws Exception {
        // Create the SalesForceUser with an existing ID
        salesForceUser.setId(1L);
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        int databaseSizeBeforeCreate = salesForceUserRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restSalesForceUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void checkNameIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesForceUserRepository.findAll().size();
        // set the field null
        salesForceUser.setName(null);

        // Create the SalesForceUser, which fails.
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        restSalesForceUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesForceUserRepository.findAll().size();
        // set the field null
        salesForceUser.setEmail(null);

        // Create the SalesForceUser, which fails.
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        restSalesForceUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void checkPhoneIsRequired() throws Exception {
        int databaseSizeBeforeTest = salesForceUserRepository.findAll().size();
        // set the field null
        salesForceUser.setPhone(null);

        // Create the SalesForceUser, which fails.
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        restSalesForceUserMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    void getAllSalesForceUsers() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        // Get all the salesForceUserList
        restSalesForceUserMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(salesForceUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].phone").value(hasItem(DEFAULT_PHONE.intValue())))
            .andExpect(jsonPath("$.[*].userRole").value(hasItem(DEFAULT_USER_ROLE.toString())))
            .andExpect(jsonPath("$.[*].dateOfJoining").value(hasItem(DEFAULT_DATE_OF_JOINING.toString())))
            .andExpect(jsonPath("$.[*].dateOfExit").value(hasItem(DEFAULT_DATE_OF_EXIT.toString())))
            .andExpect(jsonPath("$.[*].isActive").value(hasItem(DEFAULT_IS_ACTIVE.booleanValue())))
            .andExpect(jsonPath("$.[*].createdAt").value(hasItem(DEFAULT_CREATED_AT.toString())))
            .andExpect(jsonPath("$.[*].updatedAt").value(hasItem(DEFAULT_UPDATED_AT.toString())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY)))
            .andExpect(jsonPath("$.[*].updatedBy").value(hasItem(DEFAULT_UPDATED_BY)));
    }

    @Test
    @Transactional
    void getSalesForceUser() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        // Get the salesForceUser
        restSalesForceUserMockMvc
            .perform(get(ENTITY_API_URL_ID, salesForceUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(salesForceUser.getId().intValue()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.phone").value(DEFAULT_PHONE.intValue()))
            .andExpect(jsonPath("$.userRole").value(DEFAULT_USER_ROLE.toString()))
            .andExpect(jsonPath("$.dateOfJoining").value(DEFAULT_DATE_OF_JOINING.toString()))
            .andExpect(jsonPath("$.dateOfExit").value(DEFAULT_DATE_OF_EXIT.toString()))
            .andExpect(jsonPath("$.isActive").value(DEFAULT_IS_ACTIVE.booleanValue()))
            .andExpect(jsonPath("$.createdAt").value(DEFAULT_CREATED_AT.toString()))
            .andExpect(jsonPath("$.updatedAt").value(DEFAULT_UPDATED_AT.toString()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY))
            .andExpect(jsonPath("$.updatedBy").value(DEFAULT_UPDATED_BY));
    }

    @Test
    @Transactional
    void getNonExistingSalesForceUser() throws Exception {
        // Get the salesForceUser
        restSalesForceUserMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingSalesForceUser() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();

        // Update the salesForceUser
        SalesForceUser updatedSalesForceUser = salesForceUserRepository.findById(salesForceUser.getId()).get();
        // Disconnect from session so that the updates on updatedSalesForceUser are not directly saved in db
        em.detach(updatedSalesForceUser);
        updatedSalesForceUser
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .userRole(UPDATED_USER_ROLE)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfExit(UPDATED_DATE_OF_EXIT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(updatedSalesForceUser);

        restSalesForceUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesForceUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isOk());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
        SalesForceUser testSalesForceUser = salesForceUserList.get(salesForceUserList.size() - 1);
        assertThat(testSalesForceUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSalesForceUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSalesForceUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSalesForceUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testSalesForceUser.getDateOfJoining()).isEqualTo(UPDATED_DATE_OF_JOINING);
        assertThat(testSalesForceUser.getDateOfExit()).isEqualTo(UPDATED_DATE_OF_EXIT);
        assertThat(testSalesForceUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesForceUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesForceUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesForceUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesForceUser.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void putNonExistingSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, salesForceUserDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateSalesForceUserWithPatch() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();

        // Update the salesForceUser using partial update
        SalesForceUser partialUpdatedSalesForceUser = new SalesForceUser();
        partialUpdatedSalesForceUser.setId(salesForceUser.getId());

        partialUpdatedSalesForceUser
            .name(UPDATED_NAME)
            .phone(UPDATED_PHONE)
            .isActive(UPDATED_IS_ACTIVE)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restSalesForceUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesForceUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesForceUser))
            )
            .andExpect(status().isOk());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
        SalesForceUser testSalesForceUser = salesForceUserList.get(salesForceUserList.size() - 1);
        assertThat(testSalesForceUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSalesForceUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testSalesForceUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSalesForceUser.getUserRole()).isEqualTo(DEFAULT_USER_ROLE);
        assertThat(testSalesForceUser.getDateOfJoining()).isEqualTo(DEFAULT_DATE_OF_JOINING);
        assertThat(testSalesForceUser.getDateOfExit()).isEqualTo(DEFAULT_DATE_OF_EXIT);
        assertThat(testSalesForceUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesForceUser.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testSalesForceUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesForceUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesForceUser.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void fullUpdateSalesForceUserWithPatch() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();

        // Update the salesForceUser using partial update
        SalesForceUser partialUpdatedSalesForceUser = new SalesForceUser();
        partialUpdatedSalesForceUser.setId(salesForceUser.getId());

        partialUpdatedSalesForceUser
            .name(UPDATED_NAME)
            .email(UPDATED_EMAIL)
            .phone(UPDATED_PHONE)
            .userRole(UPDATED_USER_ROLE)
            .dateOfJoining(UPDATED_DATE_OF_JOINING)
            .dateOfExit(UPDATED_DATE_OF_EXIT)
            .isActive(UPDATED_IS_ACTIVE)
            .createdAt(UPDATED_CREATED_AT)
            .updatedAt(UPDATED_UPDATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedBy(UPDATED_UPDATED_BY);

        restSalesForceUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedSalesForceUser.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedSalesForceUser))
            )
            .andExpect(status().isOk());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
        SalesForceUser testSalesForceUser = salesForceUserList.get(salesForceUserList.size() - 1);
        assertThat(testSalesForceUser.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testSalesForceUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testSalesForceUser.getPhone()).isEqualTo(UPDATED_PHONE);
        assertThat(testSalesForceUser.getUserRole()).isEqualTo(UPDATED_USER_ROLE);
        assertThat(testSalesForceUser.getDateOfJoining()).isEqualTo(UPDATED_DATE_OF_JOINING);
        assertThat(testSalesForceUser.getDateOfExit()).isEqualTo(UPDATED_DATE_OF_EXIT);
        assertThat(testSalesForceUser.getIsActive()).isEqualTo(UPDATED_IS_ACTIVE);
        assertThat(testSalesForceUser.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testSalesForceUser.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testSalesForceUser.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testSalesForceUser.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    @Transactional
    void patchNonExistingSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, salesForceUserDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamSalesForceUser() throws Exception {
        int databaseSizeBeforeUpdate = salesForceUserRepository.findAll().size();
        salesForceUser.setId(count.incrementAndGet());

        // Create the SalesForceUser
        SalesForceUserDTO salesForceUserDTO = salesForceUserMapper.toDto(salesForceUser);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restSalesForceUserMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(salesForceUserDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the SalesForceUser in the database
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteSalesForceUser() throws Exception {
        // Initialize the database
        salesForceUserRepository.saveAndFlush(salesForceUser);

        int databaseSizeBeforeDelete = salesForceUserRepository.findAll().size();

        // Delete the salesForceUser
        restSalesForceUserMockMvc
            .perform(delete(ENTITY_API_URL_ID, salesForceUser.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SalesForceUser> salesForceUserList = salesForceUserRepository.findAll();
        assertThat(salesForceUserList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
