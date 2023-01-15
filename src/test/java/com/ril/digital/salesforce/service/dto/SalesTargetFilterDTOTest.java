package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class SalesTargetFilterDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SalesTargetFilterDTO.class);
        SalesTargetFilterDTO salesTargetFilterDTO1 = new SalesTargetFilterDTO();
        salesTargetFilterDTO1.setId(1L);
        SalesTargetFilterDTO salesTargetFilterDTO2 = new SalesTargetFilterDTO();
        assertThat(salesTargetFilterDTO1).isNotEqualTo(salesTargetFilterDTO2);
        salesTargetFilterDTO2.setId(salesTargetFilterDTO1.getId());
        assertThat(salesTargetFilterDTO1).isEqualTo(salesTargetFilterDTO2);
        salesTargetFilterDTO2.setId(2L);
        assertThat(salesTargetFilterDTO1).isNotEqualTo(salesTargetFilterDTO2);
        salesTargetFilterDTO1.setId(null);
        assertThat(salesTargetFilterDTO1).isNotEqualTo(salesTargetFilterDTO2);
    }
}
