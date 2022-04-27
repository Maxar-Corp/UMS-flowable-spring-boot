package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoeTest {

    @Test
    void loe() {
        assertEquals(1, Loe.ONE.value);
        assertEquals(3, Loe.THREE.value);
        assertEquals(5, Loe.FIVE.value);
    }

    @Test
    void valueOfLoe() {
        assertEquals(Loe.ONE, Loe.valueOf(1));
        assertEquals(Loe.THREE, Loe.valueOf(3));
        assertEquals(Loe.FIVE, Loe.valueOf(5));

    }
}