package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IncentiveSchemeTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncentiveScheme.class);
        IncentiveScheme incentiveScheme1 = new IncentiveScheme();
        incentiveScheme1.setId(1L);
        IncentiveScheme incentiveScheme2 = new IncentiveScheme();
        incentiveScheme2.setId(incentiveScheme1.getId());
        assertThat(incentiveScheme1).isEqualTo(incentiveScheme2);
        incentiveScheme2.setId(2L);
        assertThat(incentiveScheme1).isNotEqualTo(incentiveScheme2);
        incentiveScheme1.setId(null);
        assertThat(incentiveScheme1).isNotEqualTo(incentiveScheme2);
    }
}
