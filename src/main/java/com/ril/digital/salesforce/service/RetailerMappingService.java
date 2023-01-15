package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.RetailerMappingDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.RetailerMapping}.
 */
public interface RetailerMappingService {
    /**
     * Save a retailerMapping.
     *
     * @param retailerMappingDTO the entity to save.
     * @return the persisted entity.
     */
    RetailerMappingDTO save(RetailerMappingDTO retailerMappingDTO);

    /**
     * Updates a retailerMapping.
     *
     * @param retailerMappingDTO the entity to update.
     * @return the persisted entity.
     */
    RetailerMappingDTO update(RetailerMappingDTO retailerMappingDTO);

    /**
     * Partially updates a retailerMapping.
     *
     * @param retailerMappingDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<RetailerMappingDTO> partialUpdate(RetailerMappingDTO retailerMappingDTO);

    /**
     * Get all the retailerMappings.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<RetailerMappingDTO> findAll(Pageable pageable);

    /**
     * Get the "id" retailerMapping.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<RetailerMappingDTO> findOne(Long id);

    /**
     * Delete the "id" retailerMapping.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
