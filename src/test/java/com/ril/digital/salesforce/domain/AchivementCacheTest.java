package com.ril.digital.salesforce.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.ril.digital.salesforce.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class AchivementCacheTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(AchivementCache.class);
        AchivementCache achivementCache1 = new AchivementCache();
        achivementCache1.setId(1L);
        AchivementCache achivementCache2 = new AchivementCache();
        achivementCache2.setId(achivementCache1.getId());
        assertThat(achivementCache1).isEqualTo(achivementCache2);
        achivementCache2.setId(2L);
        assertThat(achivementCache1).isNotEqualTo(achivementCache2);
        achivementCache1.setId(null);
        assertThat(achivementCache1).isNotEqualTo(achivementCache2);
    }
}
