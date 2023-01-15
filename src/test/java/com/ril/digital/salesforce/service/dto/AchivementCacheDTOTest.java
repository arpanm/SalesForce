package com.ril.digital.salesforce.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AchivementCacheDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchivementCacheDTO.class);
        AchivementCacheDTO achivementCacheDTO1 = new AchivementCacheDTO();
        achivementCacheDTO1.setId(1L);
        AchivementCacheDTO achivementCacheDTO2 = new AchivementCacheDTO();
        assertThat(achivementCacheDTO1).isNotEqualTo(achivementCacheDTO2);
        achivementCacheDTO2.setId(achivementCacheDTO1.getId());
        assertThat(achivementCacheDTO1).isEqualTo(achivementCacheDTO2);
        achivementCacheDTO2.setId(2L);
        assertThat(achivementCacheDTO1).isNotEqualTo(achivementCacheDTO2);
        achivementCacheDTO1.setId(null);
        assertThat(achivementCacheDTO1).isNotEqualTo(achivementCacheDTO2);
    }
}
