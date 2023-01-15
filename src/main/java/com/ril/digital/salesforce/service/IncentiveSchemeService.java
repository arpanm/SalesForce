package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.IncentiveScheme}.
 */
public interface IncentiveSchemeService {
    /**
     * Save a incentiveScheme.
     *
     * @param incentiveSchemeDTO the entity to save.
     * @return the persisted entity.
     */
    IncentiveSchemeDTO save(IncentiveSchemeDTO incentiveSchemeDTO);

    /**
     * Updates a incentiveScheme.
     *
     * @param incentiveSchemeDTO the entity to update.
     * @return the persisted entity.
     */
    IncentiveSchemeDTO update(IncentiveSchemeDTO incentiveSchemeDTO);

    /**
     * Partially updates a incentiveScheme.
     *
     * @param incentiveSchemeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<IncentiveSchemeDTO> partialUpdate(IncentiveSchemeDTO incentiveSchemeDTO);

    /**
     * Get all the incentiveSchemes.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<IncentiveSchemeDTO> findAll(Pageable pageable);

    /**
     * Get the "id" incentiveScheme.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<IncentiveSchemeDTO> findOne(Long id);

    /**
     * Delete the "id" incentiveScheme.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
