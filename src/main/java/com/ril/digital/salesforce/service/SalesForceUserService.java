package com.ril.digital.salesforce.service;

import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.ril.digital.salesforce.domain.SalesForceUser}.
 */
public interface SalesForceUserService {
    /**
     * Save a salesForceUser.
     *
     * @param salesForceUserDTO the entity to save.
     * @return the persisted entity.
     */
    SalesForceUserDTO save(SalesForceUserDTO salesForceUserDTO);

    /**
     * Updates a salesForceUser.
     *
     * @param salesForceUserDTO the entity to update.
     * @return the persisted entity.
     */
    SalesForceUserDTO update(SalesForceUserDTO salesForceUserDTO);

    /**
     * Partially updates a salesForceUser.
     *
     * @param salesForceUserDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<SalesForceUserDTO> partialUpdate(SalesForceUserDTO salesForceUserDTO);

    /**
     * Get all the salesForceUsers.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SalesForceUserDTO> findAll(Pageable pageable);

    /**
     * Get the "id" salesForceUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SalesForceUserDTO> findOne(Long id);

    /**
     * Delete the "id" salesForceUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
