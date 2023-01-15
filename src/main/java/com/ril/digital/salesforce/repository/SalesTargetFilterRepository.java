package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.SalesTargetFilter;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesTargetFilter entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesTargetFilterRepository extends JpaRepository<SalesTargetFilter, Long> {}
