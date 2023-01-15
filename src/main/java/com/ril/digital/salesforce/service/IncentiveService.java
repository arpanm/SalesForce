package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.IncentiveDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.Incentive}.
 */
public interface IncentiveService {
    /**
     * Save a incentive.
     *
     * @param incentiveDTO the entity to save.
     * @return the persisted entity.
     */
    IncentiveDTO save(IncentiveDTO incentiveDTO);

    /**
     * Updates a incentive.
     *
     * @param incentiveDTO the entity to update.
     * @return the persisted entity.
     */
    IncentiveDTO update(IncentiveDTO incentiveDTO);

    /**
     * Partially updates a incentive.
     *
     * @param incentiveDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IncentiveDTO> partialUpdate(IncentiveDTO incentiveDTO);

    /**
     * Get all the incentives.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncentiveDTO> findAll(Pageable pageable);

    /**
     * Get the "id" incentive.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncentiveDTO> findOne(Long id);

    /**
     * Delete the "id" incentive.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
