package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesForceUserTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesForceUser.class);
        SalesForceUser salesForceUser1 = new SalesForceUser();
        salesForceUser1.setId(1L);
        SalesForceUser salesForceUser2 = new SalesForceUser();
        salesForceUser2.setId(salesForceUser1.getId());
        assertThat(salesForceUser1).isEqualTo(salesForceUser2);
        salesForceUser2.setId(2L);
        assertThat(salesForceUser1).isNotEqualTo(salesForceUser2);
        salesForceUser1.setId(null);
        assertThat(salesForceUser1).isNotEqualTo(salesForceUser2);
    }
}
