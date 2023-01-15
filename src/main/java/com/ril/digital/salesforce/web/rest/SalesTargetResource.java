package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.SalesTargetRepository;
import com.ril.digital.salesforce.service.SalesTargetService;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.SalesTarget}.
 */
@RestController
@RequestMapping("/api")
public class SalesTargetResource {

    private final Logger log = LoggerFactory.getLogger(SalesTargetResource.class);

    private static final String ENTITY_NAME = "salesTarget";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SalesTargetService salesTargetService;

    private final SalesTargetRepository salesTargetRepository;

    public SalesTargetResource(SalesTargetService salesTargetService, SalesTargetRepository salesTargetRepository) {
        this.salesTargetService = salesTargetService;
        this.salesTargetRepository = salesTargetRepository;
    }

    /**
     * {@code POST  /sales-targets} : Create a new salesTarget.
     *
     * @param salesTargetDTO the salesTargetDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new salesTargetDTO, or with status {@code 400 (Bad Request)} if the salesTarget has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/sales-targets")
    public ResponseEntity<SalesTargetDTO> createSalesTarget(@Valid @RequestBody SalesTargetDTO salesTargetDTO) throws URISyntaxException {
        log.debug("REST request to save SalesTarget : {}", salesTargetDTO);
        if (salesTargetDTO.getId() != null) {
            throw new BadRequestAlertException("A new salesTarget cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SalesTargetDTO result = salesTargetService.save(salesTargetDTO);
        return ResponseEntity
            .created(new URI("/api/sales-targets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /sales-targets/:id} : Updates an existing salesTarget.
     *
     * @param id the id of the salesTargetDTO to save.
     * @param salesTargetDTO the salesTargetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesTargetDTO,
     * or with status {@code 400 (Bad Request)} if the salesTargetDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the salesTargetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/sales-targets/{id}")
    public ResponseEntity<SalesTargetDTO> updateSalesTarget(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody SalesTargetDTO salesTargetDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SalesTarget : {}, {}", id, salesTargetDTO);
        if (salesTargetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesTargetDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesTargetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        SalesTargetDTO result = salesTargetService.update(salesTargetDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesTargetDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /sales-targets/:id} : Partial updates given fields of an existing salesTarget, field will ignore if it is null
     *
     * @param id the id of the salesTargetDTO to save.
     * @param salesTargetDTO the salesTargetDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated salesTargetDTO,
     * or with status {@code 400 (Bad Request)} if the salesTargetDTO is not valid,
     * or with status {@code 404 (Not Found)} if the salesTargetDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the salesTargetDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/sales-targets/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SalesTargetDTO> partialUpdateSalesTarget(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody SalesTargetDTO salesTargetDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SalesTarget partially : {}, {}", id, salesTargetDTO);
        if (salesTargetDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, salesTargetDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!salesTargetRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SalesTargetDTO> result = salesTargetService.partialUpdate(salesTargetDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, salesTargetDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /sales-targets} : get all the salesTargets.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of salesTargets in body.
     */
    @GetMapping("/sales-targets")
    public ResponseEntity<List<SalesTargetDTO>> getAllSalesTargets(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of SalesTargets");
        Page<SalesTargetDTO> page = salesTargetService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /sales-targets/:id} : get the "id" salesTarget.
     *
     * @param id the id of the salesTargetDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the salesTargetDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/sales-targets/{id}")
    public ResponseEntity<SalesTargetDTO> getSalesTarget(@PathVariable Long id) {
        log.debug("REST request to get SalesTarget : {}", id);
        Optional<SalesTargetDTO> salesTargetDTO = salesTargetService.findOne(id);
        return ResponseUtil.wrapOrNotFound(salesTargetDTO);
    }

    /**
     * {@code DELETE  /sales-targets/:id} : delete the "id" salesTarget.
     *
     * @param id the id of the salesTargetDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/sales-targets/{id}")
    public ResponseEntity<Void> deleteSalesTarget(@PathVariable Long id) {
        log.debug("REST request to delete SalesTarget : {}", id);
        salesTargetService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
