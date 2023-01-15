package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class IncentiveSchemeDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IncentiveSchemeDTO.class);
        IncentiveSchemeDTO incentiveSchemeDTO1 = new IncentiveSchemeDTO();
        incentiveSchemeDTO1.setId(1L);
        IncentiveSchemeDTO incentiveSchemeDTO2 = new IncentiveSchemeDTO();
        assertThat(incentiveSchemeDTO1).isNotEqualTo(incentiveSchemeDTO2);
        incentiveSchemeDTO2.setId(incentiveSchemeDTO1.getId());
        assertThat(incentiveSchemeDTO1).isEqualTo(incentiveSchemeDTO2);
        incentiveSchemeDTO2.setId(2L);
        assertThat(incentiveSchemeDTO1).isNotEqualTo(incentiveSchemeDTO2);
        incentiveSchemeDTO1.setId(null);
        assertThat(incentiveSchemeDTO1).isNotEqualTo(incentiveSchemeDTO2);
    }
}
