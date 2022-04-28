package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriorityTest {

    @Test
    void getValueByTitle() {
        assertEquals(Priority.HIGH, Priority.valueByTitle("High"));
        assertEquals(Priority.MEDIUM, Priority.valueByTitle("Medium"));
        assertEquals(Priority.LOW, Priority.valueByTitle("Low"));
    }

    @Test
    void getPriority() {
        assertEquals("High", Priority.HIGH.getPriority());
        assertEquals("Medium", Priority.MEDIUM.getPriority());
        assertEquals("Low", Priority.LOW.getPriority());
    }

    @Test
    void priorityToString() {
        assertEquals("High", Priority.HIGH.toString());
        assertEquals("Medium", Priority.MEDIUM.toString());
        assertEquals("Low", Priority.LOW.toString());
    }

}