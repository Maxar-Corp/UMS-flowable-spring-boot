package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StatusTest {

    @Test
    void status() {
        assertEquals("Pending", Status.PENDING.status());
        assertEquals("To Review", Status.TO_REVIEW.status());
        assertEquals("In Progress", Status.IN_PROGRESS.status());
        assertEquals("Complete", Status.COMPLETE.status());

    }

    @Test
    void valueOfStatus() {
        assertEquals(Status.PENDING, Status.valueOfStatus("Pending"));
        assertEquals(Status.TO_REVIEW, Status.valueOfStatus("To Review"));
        assertEquals(Status.IN_PROGRESS, Status.valueOfStatus("In Progress"));
        assertEquals(Status.COMPLETE, Status.valueOfStatus("Complete"));
    }

}