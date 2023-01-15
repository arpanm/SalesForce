package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesTargetDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesTargetDTO.class);
        SalesTargetDTO salesTargetDTO1 = new SalesTargetDTO();
        salesTargetDTO1.setId(1L);
        SalesTargetDTO salesTargetDTO2 = new SalesTargetDTO();
        assertThat(salesTargetDTO1).isNotEqualTo(salesTargetDTO2);
        salesTargetDTO2.setId(salesTargetDTO1.getId());
        assertThat(salesTargetDTO1).isEqualTo(salesTargetDTO2);
        salesTargetDTO2.setId(2L);
        assertThat(salesTargetDTO1).isNotEqualTo(salesTargetDTO2);
        salesTargetDTO1.setId(null);
        assertThat(salesTargetDTO1).isNotEqualTo(salesTargetDTO2);
    }
}
