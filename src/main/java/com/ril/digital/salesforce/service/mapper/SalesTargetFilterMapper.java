package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.SalesTarget;
import com.ril.digital.salesforce.domain.SalesTargetFilter;
import com.ril.digital.salesforce.service.dto.SalesTargetDTO;
import com.ril.digital.salesforce.service.dto.SalesTargetFilterDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SalesTargetFilter} and its DTO {@link SalesTargetFilterDTO}.
 */
@Mapper(componentModel = "spring")
public interface SalesTargetFilterMapper extends EntityMapper<SalesTargetFilterDTO, SalesTargetFilter> {
    @Mapping(target = "target", source = "target", qualifiedByName = "salesTargetId")
    SalesTargetFilterDTO toDto(SalesTargetFilter s);

    @Named("salesTargetId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SalesTargetDTO toDtoSalesTargetId(SalesTarget salesTarget);
}
