package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.IncentiveScheme;
import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.domain.SalesTarget;
import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link IncentiveScheme} and its DTO {@link IncentiveSchemeDTO}.
 */
@Mapper(componentModel = "spring")
public interface IncentiveSchemeMapper extends EntityMapper<IncentiveSchemeDTO, IncentiveScheme> {
    @Mapping(target = "target", source = "target", qualifiedByName = "salesTargetId")
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "salesForceUserId")
    IncentiveSchemeDTO toDto(IncentiveScheme s);

    @Named("salesTargetId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesTargetDTO toDtoSalesTargetId(SalesTarget salesTarget);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
