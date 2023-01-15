package com.ril.digital.salesforce.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RetailerMappingMapperTest {

    private RetailerMappingMapper retailerMappingMapper;

    @BeforeEach
    public void setUp() {
        retailerMappingMapper = new RetailerMappingMapperImpl();
    }
}
