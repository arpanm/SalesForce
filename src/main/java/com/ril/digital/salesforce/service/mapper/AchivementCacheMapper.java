package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.AchivementCache;
import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.service.dto.AchivementCacheDTO;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AchivementCache} and its DTO {@link AchivementCacheDTO}.
 */
@Mapper(componentModel = "spring")
public interface AchivementCacheMapper extends EntityMapper<AchivementCacheDTO, AchivementCache> {
    @Mapping(target = "salesAgent", source = "salesAgent", qualifiedByName = "salesForceUserId")
    AchivementCacheDTO toDto(AchivementCache s);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
