package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesTargetFilterTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesTargetFilter.class);
        SalesTargetFilter salesTargetFilter1 = new SalesTargetFilter();
        salesTargetFilter1.setId(1L);
        SalesTargetFilter salesTargetFilter2 = new SalesTargetFilter();
        salesTargetFilter2.setId(salesTargetFilter1.getId());
        assertThat(salesTargetFilter1).isEqualTo(salesTargetFilter2);
        salesTargetFilter2.setId(2L);
        assertThat(salesTargetFilter1).isNotEqualTo(salesTargetFilter2);
        salesTargetFilter1.setId(null);
        assertThat(salesTargetFilter1).isNotEqualTo(salesTargetFilter2);
    }
}
