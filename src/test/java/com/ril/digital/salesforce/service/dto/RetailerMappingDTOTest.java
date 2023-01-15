package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class RetailerMappingDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(RetailerMappingDTO.class);
        RetailerMappingDTO retailerMappingDTO1 = new RetailerMappingDTO();
        retailerMappingDTO1.setId(1L);
        RetailerMappingDTO retailerMappingDTO2 = new RetailerMappingDTO();
        assertThat(retailerMappingDTO1).isNotEqualTo(retailerMappingDTO2);
        retailerMappingDTO2.setId(retailerMappingDTO1.getId());
        assertThat(retailerMappingDTO1).isEqualTo(retailerMappingDTO2);
        retailerMappingDTO2.setId(2L);
        assertThat(retailerMappingDTO1).isNotEqualTo(retailerMappingDTO2);
        retailerMappingDTO1.setId(null);
        assertThat(retailerMappingDTO1).isNotEqualTo(retailerMappingDTO2);
    }
}
