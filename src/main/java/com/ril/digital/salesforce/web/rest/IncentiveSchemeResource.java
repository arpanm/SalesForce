package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.IncentiveSchemeRepository;
import com.ril.digital.salesforce.service.IncentiveSchemeService;
import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.IncentiveScheme}.
 */
@RestController
@RequestMapping("/api")
public class IncentiveSchemeResource {

    private final Logger log = LoggerFactory.getLogger(IncentiveSchemeResource.class);

    private static final String ENTITY_NAME = "incentiveScheme";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncentiveSchemeService incentiveSchemeService;

    private final IncentiveSchemeRepository incentiveSchemeRepository;

    public IncentiveSchemeResource(IncentiveSchemeService incentiveSchemeService, IncentiveSchemeRepository incentiveSchemeRepository) {
        this.incentiveSchemeService = incentiveSchemeService;
        this.incentiveSchemeRepository = incentiveSchemeRepository;
    }

    /**
     * {@code POST  /incentive-schemes} : Create a new incentiveScheme.
     *
     * @param incentiveSchemeDTO the incentiveSchemeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incentiveSchemeDTO, or with status {@code 400 (Bad Request)} if the incentiveScheme has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incentive-schemes")
    public ResponseEntity<IncentiveSchemeDTO> createIncentiveScheme(@RequestBody IncentiveSchemeDTO incentiveSchemeDTO)
        throws URISyntaxException {
        log.debug("REST request to save IncentiveScheme : {}", incentiveSchemeDTO);
        if (incentiveSchemeDTO.getId() != null) {
            throw new BadRequestAlertException("A new incentiveScheme cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncentiveSchemeDTO result = incentiveSchemeService.save(incentiveSchemeDTO);
        return ResponseEntity
            .created(new URI("/api/incentive-schemes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /incentive-schemes/:id} : Updates an existing incentiveScheme.
     *
     * @param id the id of the incentiveSchemeDTO to save.
     * @param incentiveSchemeDTO the incentiveSchemeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incentiveSchemeDTO,
     * or with status {@code 400 (Bad Request)} if the incentiveSchemeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incentiveSchemeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incentive-schemes/{id}")
    public ResponseEntity<IncentiveSchemeDTO> updateIncentiveScheme(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IncentiveSchemeDTO incentiveSchemeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update IncentiveScheme : {}, {}", id, incentiveSchemeDTO);
        if (incentiveSchemeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incentiveSchemeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incentiveSchemeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        IncentiveSchemeDTO result = incentiveSchemeService.update(incentiveSchemeDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incentiveSchemeDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /incentive-schemes/:id} : Partial updates given fields of an existing incentiveScheme, field will ignore if it is null
     *
     * @param id the id of the incentiveSchemeDTO to save.
     * @param incentiveSchemeDTO the incentiveSchemeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incentiveSchemeDTO,
     * or with status {@code 400 (Bad Request)} if the incentiveSchemeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the incentiveSchemeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the incentiveSchemeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/incentive-schemes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<IncentiveSchemeDTO> partialUpdateIncentiveScheme(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IncentiveSchemeDTO incentiveSchemeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update IncentiveScheme partially : {}, {}", id, incentiveSchemeDTO);
        if (incentiveSchemeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incentiveSchemeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incentiveSchemeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IncentiveSchemeDTO> result = incentiveSchemeService.partialUpdate(incentiveSchemeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incentiveSchemeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /incentive-schemes} : get all the incentiveSchemes.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incentiveSchemes in body.
     */
    @GetMapping("/incentive-schemes")
    public ResponseEntity<List<IncentiveSchemeDTO>> getAllIncentiveSchemes(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of IncentiveSchemes");
        Page<IncentiveSchemeDTO> page = incentiveSchemeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /incentive-schemes/:id} : get the "id" incentiveScheme.
     *
     * @param id the id of the incentiveSchemeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incentiveSchemeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incentive-schemes/{id}")
    public ResponseEntity<IncentiveSchemeDTO> getIncentiveScheme(@PathVariable Long id) {
        log.debug("REST request to get IncentiveScheme : {}", id);
        Optional<IncentiveSchemeDTO> incentiveSchemeDTO = incentiveSchemeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incentiveSchemeDTO);
    }

    /**
     * {@code DELETE  /incentive-schemes/:id} : delete the "id" incentiveScheme.
     *
     * @param id the id of the incentiveSchemeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incentive-schemes/{id}")
    public ResponseEntity<Void> deleteIncentiveScheme(@PathVariable Long id) {
        log.debug("REST request to delete IncentiveScheme : {}", id);
        incentiveSchemeService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
