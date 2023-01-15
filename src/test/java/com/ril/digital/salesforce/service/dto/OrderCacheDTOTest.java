package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderCacheDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderCacheDTO.class);
        OrderCacheDTO orderCacheDTO1 = new OrderCacheDTO();
        orderCacheDTO1.setId(1L);
        OrderCacheDTO orderCacheDTO2 = new OrderCacheDTO();
        assertThat(orderCacheDTO1).isNotEqualTo(orderCacheDTO2);
        orderCacheDTO2.setId(orderCacheDTO1.getId());
        assertThat(orderCacheDTO1).isEqualTo(orderCacheDTO2);
        orderCacheDTO2.setId(2L);
        assertThat(orderCacheDTO1).isNotEqualTo(orderCacheDTO2);
        orderCacheDTO1.setId(null);
        assertThat(orderCacheDTO1).isNotEqualTo(orderCacheDTO2);
    }
}
