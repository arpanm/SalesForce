package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.RetailerMapping;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the RetailerMapping entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RetailerMappingRepository extends JpaRepository<RetailerMapping, Long> {}
