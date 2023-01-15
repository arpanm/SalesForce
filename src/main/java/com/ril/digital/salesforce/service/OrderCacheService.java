package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.OrderCacheDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.OrderCache}.
 */
public interface OrderCacheService {
    /**
     * Save a orderCache.
     *
     * @param orderCacheDTO the entity to save.
     * @return the persisted entity.
     */
    OrderCacheDTO save(OrderCacheDTO orderCacheDTO);

    /**
     * Updates a orderCache.
     *
     * @param orderCacheDTO the entity to update.
     * @return the persisted entity.
     */
    OrderCacheDTO update(OrderCacheDTO orderCacheDTO);

    /**
     * Partially updates a orderCache.
     *
     * @param orderCacheDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<OrderCacheDTO> partialUpdate(OrderCacheDTO orderCacheDTO);

    /**
     * Get all the orderCaches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<OrderCacheDTO> findAll(Pageable pageable);

    /**
     * Get the "id" orderCache.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<OrderCacheDTO> findOne(Long id);

    /**
     * Delete the "id" orderCache.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
