package com.ril.digital.salesforce.repository;

import com.ril.digital.salesforce.domain.SalesForceUser;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the SalesForceUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SalesForceUserRepository extends JpaRepository<SalesForceUser, Long> {}
