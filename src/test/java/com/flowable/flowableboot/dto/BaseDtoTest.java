package com.flowable.flowableboot.dto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BaseDtoTest {

    @Test
    void baseDto(){
        BaseDto test = new BaseDto();

        assertNotNull(test);
        assertThat(test.getId() > 0);

        test.setId(100);

        assertEquals(100, test.getId());
    }

}