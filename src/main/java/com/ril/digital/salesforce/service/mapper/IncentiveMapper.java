package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.Incentive;
import com.ril.digital.salesforce.domain.IncentiveScheme;
import com.ril.digital.salesforce.domain.SalesForceUser;
import com.ril.digital.salesforce.service.dto.IncentiveDTO;
import com.ril.digital.salesforce.service.dto.IncentiveSchemeDTO;
import com.ril.digital.salesforce.service.dto.SalesForceUserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Incentive} and its DTO {@link IncentiveDTO}.
 */
@Mapper(componentModel = "spring")
public interface IncentiveMapper extends EntityMapper<IncentiveDTO, Incentive> {
    @Mapping(target = "scheme", source = "scheme", qualifiedByName = "incentiveSchemeId")
    @Mapping(target = "salesAgent", source = "salesAgent", qualifiedByName = "salesForceUserId")
    @Mapping(target = "approvedBy", source = "approvedBy", qualifiedByName = "salesForceUserId")
    IncentiveDTO toDto(Incentive s);

    @Named("incentiveSchemeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    IncentiveSchemeDTO toDtoIncentiveSchemeId(IncentiveScheme incentiveScheme);

    @Named("salesForceUserId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesForceUserDTO toDtoSalesForceUserId(SalesForceUser salesForceUser);
}
