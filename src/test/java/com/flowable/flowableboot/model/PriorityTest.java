package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    void getValue() {
        assertEquals(1, Priority.FIRST.getValue());
        assertEquals(3, Priority.THIRD.getValue());
        assertEquals(5, Priority.FIFTH.getValue());
    }

    @Test
    void valueOf() {
        assertEquals(Priority.FIRST, Priority.valueOf(1));
        assertEquals(Priority.THIRD, Priority.valueOf(3));
        assertEquals(Priority.FIFTH, Priority.valueOf(5));
    }
}