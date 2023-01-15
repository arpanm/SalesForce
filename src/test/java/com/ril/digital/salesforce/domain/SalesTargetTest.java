package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesTargetTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesTarget.class);
        SalesTarget salesTarget1 = new SalesTarget();
        salesTarget1.setId(1L);
        SalesTarget salesTarget2 = new SalesTarget();
        salesTarget2.setId(salesTarget1.getId());
        assertThat(salesTarget1).isEqualTo(salesTarget2);
        salesTarget2.setId(2L);
        assertThat(salesTarget1).isNotEqualTo(salesTarget2);
        salesTarget1.setId(null);
        assertThat(salesTarget1).isNotEqualTo(salesTarget2);
    }
}
