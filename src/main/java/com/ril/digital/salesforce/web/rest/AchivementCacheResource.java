package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.AchivementCacheRepository;
import com.ril.digital.salesforce.service.AchivementCacheService;
import com.ril.digital.salesforce.service.dto.AchivementCacheDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.AchivementCache}.
 */
@RestController
@RequestMapping("/api")
public class AchivementCacheResource {

    private final Logger log = LoggerFactory.getLogger(AchivementCacheResource.class);

    private static final String ENTITY_NAME = "achivementCache";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AchivementCacheService achivementCacheService;

    private final AchivementCacheRepository achivementCacheRepository;

    public AchivementCacheResource(AchivementCacheService achivementCacheService, AchivementCacheRepository achivementCacheRepository) {
        this.achivementCacheService = achivementCacheService;
        this.achivementCacheRepository = achivementCacheRepository;
    }

    /**
     * {@code POST  /achivement-caches} : Create a new achivementCache.
     *
     * @param achivementCacheDTO the achivementCacheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new achivementCacheDTO, or with status {@code 400 (Bad Request)} if the achivementCache has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/achivement-caches")
    public ResponseEntity<AchivementCacheDTO> createAchivementCache(@RequestBody AchivementCacheDTO achivementCacheDTO)
        throws URISyntaxException {
        log.debug("REST request to save AchivementCache : {}", achivementCacheDTO);
        if (achivementCacheDTO.getId() != null) {
            throw new BadRequestAlertException("A new achivementCache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AchivementCacheDTO result = achivementCacheService.save(achivementCacheDTO);
        return ResponseEntity
            .created(new URI("/api/achivement-caches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /achivement-caches/:id} : Updates an existing achivementCache.
     *
     * @param id the id of the achivementCacheDTO to save.
     * @param achivementCacheDTO the achivementCacheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated achivementCacheDTO,
     * or with status {@code 400 (Bad Request)} if the achivementCacheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the achivementCacheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/achivement-caches/{id}")
    public ResponseEntity<AchivementCacheDTO> updateAchivementCache(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AchivementCacheDTO achivementCacheDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AchivementCache : {}, {}", id, achivementCacheDTO);
        if (achivementCacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, achivementCacheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!achivementCacheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        AchivementCacheDTO result = achivementCacheService.update(achivementCacheDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, achivementCacheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /achivement-caches/:id} : Partial updates given fields of an existing achivementCache, field will ignore if it is null
     *
     * @param id the id of the achivementCacheDTO to save.
     * @param achivementCacheDTO the achivementCacheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated achivementCacheDTO,
     * or with status {@code 400 (Bad Request)} if the achivementCacheDTO is not valid,
     * or with status {@code 404 (Not Found)} if the achivementCacheDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the achivementCacheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/achivement-caches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AchivementCacheDTO> partialUpdateAchivementCache(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AchivementCacheDTO achivementCacheDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AchivementCache partially : {}, {}", id, achivementCacheDTO);
        if (achivementCacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, achivementCacheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!achivementCacheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AchivementCacheDTO> result = achivementCacheService.partialUpdate(achivementCacheDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, achivementCacheDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /achivement-caches} : get all the achivementCaches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of achivementCaches in body.
     */
    @GetMapping("/achivement-caches")
    public ResponseEntity<List<AchivementCacheDTO>> getAllAchivementCaches(
        @org.springdoc.api.annotations.ParameterObject Pageable pageable
    ) {
        log.debug("REST request to get a page of AchivementCaches");
        Page<AchivementCacheDTO> page = achivementCacheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /achivement-caches/:id} : get the "id" achivementCache.
     *
     * @param id the id of the achivementCacheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the achivementCacheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/achivement-caches/{id}")
    public ResponseEntity<AchivementCacheDTO> getAchivementCache(@PathVariable Long id) {
        log.debug("REST request to get AchivementCache : {}", id);
        Optional<AchivementCacheDTO> achivementCacheDTO = achivementCacheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(achivementCacheDTO);
    }

    /**
     * {@code DELETE  /achivement-caches/:id} : delete the "id" achivementCache.
     *
     * @param id the id of the achivementCacheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/achivement-caches/{id}")
    public ResponseEntity<Void> deleteAchivementCache(@PathVariable Long id) {
        log.debug("REST request to delete AchivementCache : {}", id);
        achivementCacheService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
