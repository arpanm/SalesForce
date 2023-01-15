package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.Incentive;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Incentive entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncentiveRepository extends JpaRepository<Incentive, Long> {}
