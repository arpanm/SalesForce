package com.ril.digital.salesforce.service.mapper;

import com.ril.digital.salesforce.domain.OrderCache;
import com.ril.digital.salesforce.service.dto.OrderCacheDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link OrderCache} and its DTO {@link OrderCacheDTO}.
 */
@Mapper(componentModel = "spring")
public interface OrderCacheMapper extends EntityMapper<OrderCacheDTO, OrderCache> {}
