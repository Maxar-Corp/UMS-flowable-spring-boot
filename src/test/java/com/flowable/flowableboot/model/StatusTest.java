package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void status() {
        assertEquals("Pending", Status.PENDING.getStatus());
        assertEquals("To Review", Status.TO_REVIEW.getStatus());
        assertEquals("In Progress", Status.IN_PROGRESS.getStatus());
        assertEquals("Complete", Status.COMPLETE.getStatus());

    }

    @Test
    void statusToString() {
        assertEquals("Pending", Status.PENDING.toString());
        assertEquals("To Review", Status.TO_REVIEW.toString());
        assertEquals("In Progress", Status.IN_PROGRESS.toString());
        assertEquals("Complete", Status.COMPLETE.toString());

    }

    @Test
    void valueOfStatus() {
        assertEquals(Status.PENDING, Status.valueByTitle("Pending"));
        assertEquals(Status.TO_REVIEW, Status.valueByTitle("To Review"));
        assertEquals(Status.IN_PROGRESS, Status.valueByTitle("In Progress"));
        assertEquals(Status.COMPLETE, Status.valueByTitle("Complete"));
    }

}