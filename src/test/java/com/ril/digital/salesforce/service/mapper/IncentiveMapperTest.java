package com.ril.digital.salesforce.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IncentiveMapperTest {

    private IncentiveMapper incentiveMapper;

    @BeforeEach
    public void setUp() {
        incentiveMapper = new IncentiveMapperImpl();
    }
}
