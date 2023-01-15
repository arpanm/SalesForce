package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.SalesTarget;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesTarget entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesTargetRepository extends JpaRepository<SalesTarget, Long> {}
