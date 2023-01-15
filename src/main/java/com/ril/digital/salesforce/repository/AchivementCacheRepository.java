package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.AchivementCache;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the AchivementCache entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AchivementCacheRepository extends JpaRepository<AchivementCache, Long> {}
