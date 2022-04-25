package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoeTest {

    @Test
    void loe() {
        assertEquals("High", Loe.HIGH.loe());
        assertEquals("Medium", Loe.MEDIUM.loe());
        assertEquals("Low", Loe.LOW.loe());
    }

    @Test
    void valueOfLoe() {
        assertEquals(Loe.HIGH, Loe.valueOfLoe("High"));
        assertEquals(Loe.MEDIUM, Loe.valueOfLoe("Medium"));
        assertEquals(Loe.LOW, Loe.valueOfLoe("Low"));

    }
}