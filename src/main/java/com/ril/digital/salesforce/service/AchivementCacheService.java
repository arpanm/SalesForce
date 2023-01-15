package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.AchivementCacheDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.AchivementCache}.
 */
public interface AchivementCacheService {
    /**
     * Save a achivementCache.
     *
     * @param achivementCacheDTO the entity to save.
     * @return the persisted entity.
     */
    AchivementCacheDTO save(AchivementCacheDTO achivementCacheDTO);

    /**
     * Updates a achivementCache.
     *
     * @param achivementCacheDTO the entity to update.
     * @return the persisted entity.
     */
    AchivementCacheDTO update(AchivementCacheDTO achivementCacheDTO);

    /**
     * Partially updates a achivementCache.
     *
     * @param achivementCacheDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<AchivementCacheDTO> partialUpdate(AchivementCacheDTO achivementCacheDTO);

    /**
     * Get all the achivementCaches.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<AchivementCacheDTO> findAll(Pageable pageable);

    /**
     * Get the "id" achivementCache.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<AchivementCacheDTO> findOne(Long id);

    /**
     * Delete the "id" achivementCache.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
