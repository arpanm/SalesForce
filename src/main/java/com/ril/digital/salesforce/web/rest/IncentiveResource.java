package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.IncentiveRepository;
import com.ril.digital.salesforce.service.IncentiveService;
import com.ril.digital.salesforce.service.dto.IncentiveDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.Incentive}.
 */
@RestController
@RequestMapping("/api")
public class IncentiveResource {

    private final Logger log = LoggerFactory.getLogger(IncentiveResource.class);

    private static final String ENTITY_NAME = "incentive";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncentiveService incentiveService;

    private final IncentiveRepository incentiveRepository;

    public IncentiveResource(IncentiveService incentiveService, IncentiveRepository incentiveRepository) {
        this.incentiveService = incentiveService;
        this.incentiveRepository = incentiveRepository;
    }

    /**
     * {@code POST  /incentives} : Create a new incentive.
     *
     * @param incentiveDTO the incentiveDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incentiveDTO, or with status {@code 400 (Bad Request)} if the incentive has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incentives")
    public ResponseEntity<IncentiveDTO> createIncentive(@RequestBody IncentiveDTO incentiveDTO) throws URISyntaxException {
        log.debug("REST request to save Incentive : {}", incentiveDTO);
        if (incentiveDTO.getId() != null) {
            throw new BadRequestAlertException("A new incentive cannot already have an ID", ENTITY_NAME, "idexists");
        }
        IncentiveDTO result = incentiveService.save(incentiveDTO);
        return ResponseEntity
            .created(new URI("/api/incentives/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /incentives/:id} : Updates an existing incentive.
     *
     * @param id the id of the incentiveDTO to save.
     * @param incentiveDTO the incentiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incentiveDTO,
     * or with status {@code 400 (Bad Request)} if the incentiveDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incentiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incentives/{id}")
    public ResponseEntity<IncentiveDTO> updateIncentive(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IncentiveDTO incentiveDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Incentive : {}, {}", id, incentiveDTO);
        if (incentiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incentiveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incentiveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        IncentiveDTO result = incentiveService.update(incentiveDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incentiveDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /incentives/:id} : Partial updates given fields of an existing incentive, field will ignore if it is null
     *
     * @param id the id of the incentiveDTO to save.
     * @param incentiveDTO the incentiveDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incentiveDTO,
     * or with status {@code 400 (Bad Request)} if the incentiveDTO is not valid,
     * or with status {@code 404 (Not Found)} if the incentiveDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the incentiveDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/incentives/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<IncentiveDTO> partialUpdateIncentive(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody IncentiveDTO incentiveDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Incentive partially : {}, {}", id, incentiveDTO);
        if (incentiveDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incentiveDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!incentiveRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<IncentiveDTO> result = incentiveService.partialUpdate(incentiveDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, incentiveDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /incentives} : get all the incentives.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incentives in body.
     */
    @GetMapping("/incentives")
    public ResponseEntity<List<IncentiveDTO>> getAllIncentives(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Incentives");
        Page<IncentiveDTO> page = incentiveService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /incentives/:id} : get the "id" incentive.
     *
     * @param id the id of the incentiveDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incentiveDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incentives/{id}")
    public ResponseEntity<IncentiveDTO> getIncentive(@PathVariable Long id) {
        log.debug("REST request to get Incentive : {}", id);
        Optional<IncentiveDTO> incentiveDTO = incentiveService.findOne(id);
        return ResponseUtil.wrapOrNotFound(incentiveDTO);
    }

    /**
     * {@code DELETE  /incentives/:id} : delete the "id" incentive.
     *
     * @param id the id of the incentiveDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incentives/{id}")
    public ResponseEntity<Void> deleteIncentive(@PathVariable Long id) {
        log.debug("REST request to delete Incentive : {}", id);
        incentiveService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
