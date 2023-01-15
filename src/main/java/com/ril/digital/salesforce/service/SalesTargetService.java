package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.SalesTarget}.
 */
public interface SalesTargetService {
    /**
     * Save a salesTarget.
     *
     * @param salesTargetDTO the entity to save.
     * @return the persisted entity.
     */
    SalesTargetDTO save(SalesTargetDTO salesTargetDTO);

    /**
     * Updates a salesTarget.
     *
     * @param salesTargetDTO the entity to update.
     * @return the persisted entity.
     */
    SalesTargetDTO update(SalesTargetDTO salesTargetDTO);

    /**
     * Partially updates a salesTarget.
     *
     * @param salesTargetDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesTargetDTO> partialUpdate(SalesTargetDTO salesTargetDTO);

    /**
     * Get all the salesTargets.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesTargetDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesTarget.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesTargetDTO> findOne(Long id);

    /**
     * Delete the "id" salesTarget.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
