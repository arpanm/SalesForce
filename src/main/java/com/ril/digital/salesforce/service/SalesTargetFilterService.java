package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.SalesTargetFilterDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.SalesTargetFilter}.
 */
public interface SalesTargetFilterService {
    /**
     * Save a salesTargetFilter.
     *
     * @param salesTargetFilterDTO the entity to save.
     * @return the persisted entity.
     */
    SalesTargetFilterDTO save(SalesTargetFilterDTO salesTargetFilterDTO);

    /**
     * Updates a salesTargetFilter.
     *
     * @param salesTargetFilterDTO the entity to update.
     * @return the persisted entity.
     */
    SalesTargetFilterDTO update(SalesTargetFilterDTO salesTargetFilterDTO);

    /**
     * Partially updates a salesTargetFilter.
     *
     * @param salesTargetFilterDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesTargetFilterDTO> partialUpdate(SalesTargetFilterDTO salesTargetFilterDTO);

    /**
     * Get all the salesTargetFilters.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesTargetFilterDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesTargetFilter.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesTargetFilterDTO> findOne(Long id);

    /**
     * Delete the "id" salesTargetFilter.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
