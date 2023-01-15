package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesForceUser} and its DTO {@link SalesForceUserDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesForceUserMapper extends EntityMapper<SalesForceUserDTO, SalesForceUser> {
    @Mapping(target = "manager", source = "manager", qualifiedByName = "salesForceUserId")
    SalesForceUserDTO toDto(SalesForceUser s);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
