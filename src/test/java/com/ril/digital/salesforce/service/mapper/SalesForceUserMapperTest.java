package com.ril.digital.salesforce.service.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SalesForceUserMapperTest {

    private SalesForceUserMapper salesForceUserMapper;

    @BeforeEach
    public void setUp() {
        salesForceUserMapper = new SalesForceUserMapperImpl();
    }
}
