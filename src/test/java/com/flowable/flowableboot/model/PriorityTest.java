package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    void getValue() {
        assertEquals(1, Priority.FIRST.getValue());
        assertEquals(2, Priority.SECOND.getValue());
        assertEquals(3, Priority.THIRD.getValue());
    }

    @Test
    void valueOf() {
        assertEquals(Priority.FIRST, Priority.valueOf(1));
        assertEquals(Priority.SECOND, Priority.valueOf(2));
        assertEquals(Priority.THIRD, Priority.valueOf(3));
    }
}