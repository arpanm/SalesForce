package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.RetailerMappingRepository;
import com.ril.digital.salesforce.service.RetailerMappingService;
import com.ril.digital.salesforce.service.dto.RetailerMappingDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.RetailerMapping}.
 */
@RestController
@RequestMapping("/api")
public class RetailerMappingResource {

    private final Logger log = LoggerFactory.getLogger(RetailerMappingResource.class);

    private static final String ENTITY_NAME = "retailerMapping";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RetailerMappingService retailerMappingService;

    private final RetailerMappingRepository retailerMappingRepository;

    public RetailerMappingResource(RetailerMappingService retailerMappingService, RetailerMappingRepository retailerMappingRepository) {
        this.retailerMappingService = retailerMappingService;
        this.retailerMappingRepository = retailerMappingRepository;
    }

    /**
     * {@code POST  /retailer-mappings} : Create a new retailerMapping.
     *
     * @param retailerMappingDTO the retailerMappingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new retailerMappingDTO, or with status {@code 400 (Bad Request)} if the retailerMapping has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/retailer-mappings")
    public ResponseEntity<RetailerMappingDTO> createRetailerMapping(@RequestBody RetailerMappingDTO retailerMappingDTO)
        throws URISyntaxException {
        log.debug("REST request to save RetailerMapping : {}", retailerMappingDTO);
        if (retailerMappingDTO.getId() != null) {
            throw new BadRequestAlertException("A new retailerMapping cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RetailerMappingDTO result = retailerMappingService.save(retailerMappingDTO);
        return ResponseEntity
            .created(new URI("/api/retailer-mappings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /retailer-mappings/:id} : Updates an existing retailerMapping.
     *
     * @param id the id of the retailerMappingDTO to save.
     * @param retailerMappingDTO the retailerMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated retailerMappingDTO,
     * or with status {@code 400 (Bad Request)} if the retailerMappingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the retailerMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/retailer-mappings/{id}")
    public ResponseEntity<RetailerMappingDTO> updateRetailerMapping(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RetailerMappingDTO retailerMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RetailerMapping : {}, {}", id, retailerMappingDTO);
        if (retailerMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, retailerMappingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!retailerMappingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RetailerMappingDTO result = retailerMappingService.update(retailerMappingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, retailerMappingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /retailer-mappings/:id} : Partial updates given fields of an existing retailerMapping, field will ignore if it is null
     *
     * @param id the id of the retailerMappingDTO to save.
     * @param retailerMappingDTO the retailerMappingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated retailerMappingDTO,
     * or with status {@code 400 (Bad Request)} if the retailerMappingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the retailerMappingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the retailerMappingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/retailer-mappings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RetailerMappingDTO> partialUpdateRetailerMapping(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RetailerMappingDTO retailerMappingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RetailerMapping partially : {}, {}", id, retailerMappingDTO);
        if (retailerMappingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, retailerMappingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!retailerMappingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RetailerMappingDTO> result = retailerMappingService.partialUpdate(retailerMappingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, retailerMappingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /retailer-mappings} : get all the retailerMappings.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of retailerMappings in body.
     */
    @GetMapping("/retailer-mappings")
    public ResponseEntity<List<RetailerMappingDTO>> getAllRetailerMappings(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of RetailerMappings");
        Page<RetailerMappingDTO> page = retailerMappingService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /retailer-mappings/:id} : get the "id" retailerMapping.
     *
     * @param id the id of the retailerMappingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the retailerMappingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/retailer-mappings/{id}")
    public ResponseEntity<RetailerMappingDTO> getRetailerMapping(@PathVariable Long id) {
        log.debug("REST request to get RetailerMapping : {}", id);
        Optional<RetailerMappingDTO> retailerMappingDTO = retailerMappingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(retailerMappingDTO);
    }

    /**
     * {@code DELETE  /retailer-mappings/:id} : delete the "id" retailerMapping.
     *
     * @param id the id of the retailerMappingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/retailer-mappings/{id}")
    public ResponseEntity<Void> deleteRetailerMapping(@PathVariable Long id) {
        log.debug("REST request to delete RetailerMapping : {}", id);
        retailerMappingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
