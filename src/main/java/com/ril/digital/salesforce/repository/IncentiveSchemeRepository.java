package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.IncentiveScheme;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the IncentiveScheme entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncentiveSchemeRepository extends JpaRepository<IncentiveScheme, Long> {}
