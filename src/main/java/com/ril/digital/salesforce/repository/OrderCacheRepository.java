package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.OrderCache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the OrderCache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderCacheRepository extends JpaRepository<OrderCache, Long> {}
