package com.ril.digital.salesforce.web.rest;

import com.ril.digital.salesforce.repository.OrderCacheRepository;
import com.ril.digital.salesforce.service.OrderCacheService;
import com.ril.digital.salesforce.service.dto.OrderCacheDTO;
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
 * REST controller for managing {@link com.ril.digital.salesforce.domain.OrderCache}.
 */
@RestController
@RequestMapping("/api")
public class OrderCacheResource {

    private final Logger log = LoggerFactory.getLogger(OrderCacheResource.class);

    private static final String ENTITY_NAME = "orderCache";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderCacheService orderCacheService;

    private final OrderCacheRepository orderCacheRepository;

    public OrderCacheResource(OrderCacheService orderCacheService, OrderCacheRepository orderCacheRepository) {
        this.orderCacheService = orderCacheService;
        this.orderCacheRepository = orderCacheRepository;
    }

    /**
     * {@code POST  /order-caches} : Create a new orderCache.
     *
     * @param orderCacheDTO the orderCacheDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderCacheDTO, or with status {@code 400 (Bad Request)} if the orderCache has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/order-caches")
    public ResponseEntity<OrderCacheDTO> createOrderCache(@RequestBody OrderCacheDTO orderCacheDTO) throws URISyntaxException {
        log.debug("REST request to save OrderCache : {}", orderCacheDTO);
        if (orderCacheDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderCache cannot already have an ID", ENTITY_NAME, "idexists");
        }
        OrderCacheDTO result = orderCacheService.save(orderCacheDTO);
        return ResponseEntity
            .created(new URI("/api/order-caches/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /order-caches/:id} : Updates an existing orderCache.
     *
     * @param id the id of the orderCacheDTO to save.
     * @param orderCacheDTO the orderCacheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderCacheDTO,
     * or with status {@code 400 (Bad Request)} if the orderCacheDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderCacheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/order-caches/{id}")
    public ResponseEntity<OrderCacheDTO> updateOrderCache(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderCacheDTO orderCacheDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderCache : {}, {}", id, orderCacheDTO);
        if (orderCacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderCacheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderCacheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        OrderCacheDTO result = orderCacheService.update(orderCacheDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderCacheDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /order-caches/:id} : Partial updates given fields of an existing orderCache, field will ignore if it is null
     *
     * @param id the id of the orderCacheDTO to save.
     * @param orderCacheDTO the orderCacheDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderCacheDTO,
     * or with status {@code 400 (Bad Request)} if the orderCacheDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderCacheDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderCacheDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/order-caches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderCacheDTO> partialUpdateOrderCache(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderCacheDTO orderCacheDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderCache partially : {}, {}", id, orderCacheDTO);
        if (orderCacheDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderCacheDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderCacheRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderCacheDTO> result = orderCacheService.partialUpdate(orderCacheDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderCacheDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-caches} : get all the orderCaches.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderCaches in body.
     */
    @GetMapping("/order-caches")
    public ResponseEntity<List<OrderCacheDTO>> getAllOrderCaches(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of OrderCaches");
        Page<OrderCacheDTO> page = orderCacheService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /order-caches/:id} : get the "id" orderCache.
     *
     * @param id the id of the orderCacheDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderCacheDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/order-caches/{id}")
    public ResponseEntity<OrderCacheDTO> getOrderCache(@PathVariable Long id) {
        log.debug("REST request to get OrderCache : {}", id);
        Optional<OrderCacheDTO> orderCacheDTO = orderCacheService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderCacheDTO);
    }

    /**
     * {@code DELETE  /order-caches/:id} : delete the "id" orderCache.
     *
     * @param id the id of the orderCacheDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/order-caches/{id}")
    public ResponseEntity<Void> deleteOrderCache(@PathVariable Long id) {
        log.debug("REST request to delete OrderCache : {}", id);
        orderCacheService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
