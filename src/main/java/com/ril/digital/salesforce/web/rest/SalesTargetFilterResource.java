package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.SalesTargetFilterRepository;
import com.ril.digital.salesforce.service.SalesTargetFilterService;
import com.ril.digital.salesforce.service.dto.SalesTargetFilterDTO;
import com.ril.digital.salesforce.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.SalesTargetFilter}.
 */
@RestController
@RequestMapping("/api")
public class SalesTargetFilterResource {

    private final Logger log = LoggerFactory.getLogger(SalesTargetFilterResource.class);

    private static final String ENTITY_NAME = "salesTargetFilter";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesTargetFilterService salesTargetFilterService;

    private final SalesTargetFilterRepository salesTargetFilterRepository;

    public SalesTargetFilterResource(
        SalesTargetFilterService salesTargetFilterService,
        SalesTargetFilterRepository salesTargetFilterRepository
    ) {
        this.salesTargetFilterService = salesTargetFilterService;
        this.salesTargetFilterRepository = salesTargetFilterRepository;
    }

    /**
     * {@code POST  /sales-target-filters} : Create a new salesTargetFilter.
     *
     * @param salesTargetFilterDTO the salesTargetFilterDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesTargetFilterDTO, or with status {@code 400 (Bad Request)} if the salesTargetFilter has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-target-filters")
    public ResponseEntity<SalesTargetFilterDTO> createSalesTargetFilter(@RequestBody SalesTargetFilterDTO salesTargetFilterDTO)
        throws URISyntaxException {
        log.debug("REST request to save SalesTargetFilter : {}", salesTargetFilterDTO);
        if (salesTargetFilterDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesTargetFilter cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesTargetFilterDTO result = salesTargetFilterService.save(salesTargetFilterDTO);
        return ResponseEntity
            .created(new URI("/api/sales-target-filters/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-target-filters/:id} : Updates an existing salesTargetFilter.
     *
     * @param id the id of the salesTargetFilterDTO to save.
     * @param salesTargetFilterDTO the salesTargetFilterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesTargetFilterDTO,
     * or with status {@code 400 (Bad Request)} if the salesTargetFilterDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesTargetFilterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-target-filters/{id}")
    public ResponseEntity<SalesTargetFilterDTO> updateSalesTargetFilter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesTargetFilterDTO salesTargetFilterDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesTargetFilter : {}, {}", id, salesTargetFilterDTO);
        if (salesTargetFilterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesTargetFilterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesTargetFilterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesTargetFilterDTO result = salesTargetFilterService.update(salesTargetFilterDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesTargetFilterDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sales-target-filters/:id} : Partial updates given fields of an existing salesTargetFilter, field will ignore if it is null
     *
     * @param id the id of the salesTargetFilterDTO to save.
     * @param salesTargetFilterDTO the salesTargetFilterDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesTargetFilterDTO,
     * or with status {@code 400 (Bad Request)} if the salesTargetFilterDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesTargetFilterDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesTargetFilterDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-target-filters/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesTargetFilterDTO> partialUpdateSalesTargetFilter(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SalesTargetFilterDTO salesTargetFilterDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesTargetFilter partially : {}, {}", id, salesTargetFilterDTO);
        if (salesTargetFilterDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesTargetFilterDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesTargetFilterRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesTargetFilterDTO> result = salesTargetFilterService.partialUpdate(salesTargetFilterDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesTargetFilterDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sales-target-filters} : get all the salesTargetFilters.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesTargetFilters in body.
     */
    @GetMapping("/sales-target-filters")
    public ResponseEntity<List<SalesTargetFilterDTO>> getAllSalesTargetFilters(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of SalesTargetFilters");
        Page<SalesTargetFilterDTO> page = salesTargetFilterService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-target-filters/:id} : get the "id" salesTargetFilter.
     *
     * @param id the id of the salesTargetFilterDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesTargetFilterDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-target-filters/{id}")
    public ResponseEntity<SalesTargetFilterDTO> getSalesTargetFilter(@PathVariable Long id) {
        log.debug("REST request to get SalesTargetFilter : {}", id);
        Optional<SalesTargetFilterDTO> salesTargetFilterDTO = salesTargetFilterService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesTargetFilterDTO);
    }

    /**
     * {@code DELETE  /sales-target-filters/:id} : delete the "id" salesTargetFilter.
     *
     * @param id the id of the salesTargetFilterDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-target-filters/{id}")
    public ResponseEntity<Void> deleteSalesTargetFilter(@PathVariable Long id) {
        log.debug("REST request to delete SalesTargetFilter : {}", id);
        salesTargetFilterService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
