package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.SalesForceUserRepository;
import com.ril.digital.salesforce.service.SalesForceUserService;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import com.ril.digital.salesforce.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.ril.digital.salesforce.domain.SalesForceUser}.
 */
@RestController
@RequestMapping("/api")
public class SalesForceUserResource {

    private final Logger log = LoggerFactory.getLogger(SalesForceUserResource.class);

    private static final String ENTITY_NAME = "salesForceUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesForceUserService salesForceUserService;

    private final SalesForceUserRepository salesForceUserRepository;

    public SalesForceUserResource(SalesForceUserService salesForceUserService, SalesForceUserRepository salesForceUserRepository) {
        this.salesForceUserService = salesForceUserService;
        this.salesForceUserRepository = salesForceUserRepository;
    }

    /**
     * {@code POST  /sales-force-users} : Create a new salesForceUser.
     *
     * @param salesForceUserDTO the salesForceUserDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesForceUserDTO, or with status {@code 400 (Bad Request)} if the salesForceUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-force-users")
    public ResponseEntity<SalesForceUserDTO> createSalesForceUser(@Valid @RequestBody SalesForceUserDTO salesForceUserDTO)
        throws URISyntaxException {
        log.debug("REST request to save SalesForceUser : {}", salesForceUserDTO);
        if (salesForceUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesForceUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesForceUserDTO result = salesForceUserService.save(salesForceUserDTO);
        return ResponseEntity
            .created(new URI("/api/sales-force-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-force-users/:id} : Updates an existing salesForceUser.
     *
     * @param id the id of the salesForceUserDTO to save.
     * @param salesForceUserDTO the salesForceUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesForceUserDTO,
     * or with status {@code 400 (Bad Request)} if the salesForceUserDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesForceUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-force-users/{id}")
    public ResponseEntity<SalesForceUserDTO> updateSalesForceUser(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SalesForceUserDTO salesForceUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesForceUser : {}, {}", id, salesForceUserDTO);
        if (salesForceUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesForceUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesForceUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesForceUserDTO result = salesForceUserService.update(salesForceUserDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesForceUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sales-force-users/:id} : Partial updates given fields of an existing salesForceUser, field will ignore if it is null
     *
     * @param id the id of the salesForceUserDTO to save.
     * @param salesForceUserDTO the salesForceUserDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesForceUserDTO,
     * or with status {@code 400 (Bad Request)} if the salesForceUserDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesForceUserDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesForceUserDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-force-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesForceUserDTO> partialUpdateSalesForceUser(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SalesForceUserDTO salesForceUserDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesForceUser partially : {}, {}", id, salesForceUserDTO);
        if (salesForceUserDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesForceUserDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesForceUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesForceUserDTO> result = salesForceUserService.partialUpdate(salesForceUserDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesForceUserDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sales-force-users} : get all the salesForceUsers.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesForceUsers in body.
     */
    @GetMapping("/sales-force-users")
    public ResponseEntity<List<SalesForceUserDTO>> getAllSalesForceUsers(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SalesForceUsers");
        Page<SalesForceUserDTO> page = salesForceUserService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-force-users/:id} : get the "id" salesForceUser.
     *
     * @param id the id of the salesForceUserDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesForceUserDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-force-users/{id}")
    public ResponseEntity<SalesForceUserDTO> getSalesForceUser(@PathVariable Long id) {
        log.debug("REST request to get SalesForceUser : {}", id);
        Optional<SalesForceUserDTO> salesForceUserDTO = salesForceUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesForceUserDTO);
    }

    /**
     * {@code DELETE  /sales-force-users/:id} : delete the "id" salesForceUser.
     *
     * @param id the id of the salesForceUserDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-force-users/{id}")
    public ResponseEntity<Void> deleteSalesForceUser(@PathVariable Long id) {
        log.debug("REST request to delete SalesForceUser : {}", id);
        salesForceUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
