package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RetailerMappingTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetailerMapping.class);
        RetailerMapping retailerMapping1 = new RetailerMapping();
        retailerMapping1.setId(1L);
        RetailerMapping retailerMapping2 = new RetailerMapping();
        retailerMapping2.setId(retailerMapping1.getId());
        assertThat(retailerMapping1).isEqualTo(retailerMapping2);
        retailerMapping2.setId(2L);
        assertThat(retailerMapping1).isNotEqualTo(retailerMapping2);
        retailerMapping1.setId(null);
        assertThat(retailerMapping1).isNotEqualTo(retailerMapping2);
    }
}
