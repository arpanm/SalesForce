package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.RetailerMapping;
import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.service.dto.RetailerMappingDTO;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link RetailerMapping} and its DTO {@link RetailerMappingDTO}.
 */
@Mapper(componentModel = "spring")
public interface RetailerMappingMapper extends EntityMapper<RetailerMappingDTO, RetailerMapping> {
    @Mapping(target = "relationshipManager", source = "relationshipManager", qualifiedByName = "salesForceUserId")
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "salesForceUserId")
    RetailerMappingDTO toDto(RetailerMapping s);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
