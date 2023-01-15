package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OrderCacheTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(OrderCache.class);
        OrderCache orderCache1 = new OrderCache();
        orderCache1.setId(1L);
        OrderCache orderCache2 = new OrderCache();
        orderCache2.setId(orderCache1.getId());
        assertThat(orderCache1).isEqualTo(orderCache2);
        orderCache2.setId(2L);
        assertThat(orderCache1).isNotEqualTo(orderCache2);
        orderCache1.setId(null);
        assertThat(orderCache1).isNotEqualTo(orderCache2);
    }
}
