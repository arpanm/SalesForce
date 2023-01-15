package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesForceUserDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesForceUserDTO.class);
        SalesForceUserDTO salesForceUserDTO1 = new SalesForceUserDTO();
        salesForceUserDTO1.setId(1L);
        SalesForceUserDTO salesForceUserDTO2 = new SalesForceUserDTO();
        assertThat(salesForceUserDTO1).isNotEqualTo(salesForceUserDTO2);
        salesForceUserDTO2.setId(salesForceUserDTO1.getId());
        assertThat(salesForceUserDTO1).isEqualTo(salesForceUserDTO2);
        salesForceUserDTO2.setId(2L);
        assertThat(salesForceUserDTO1).isNotEqualTo(salesForceUserDTO2);
        salesForceUserDTO1.setId(null);
        assertThat(salesForceUserDTO1).isNotEqualTo(salesForceUserDTO2);
    }
}
