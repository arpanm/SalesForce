package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.domain.SalesTarget;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesTarget} and its DTO {@link SalesTargetDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesTargetMapper extends EntityMapper<SalesTargetDTO, SalesTarget> {
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "salesForceUserId")
    SalesTargetDTO toDto(SalesTarget s);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
