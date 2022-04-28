package com.flowable.flowableboot.model;

import org.junit.jupiter.api.Test;
import java.time.Instant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UmsTaskTest {

    @Test
    void umsTask(){
        UmsTask umsTask = new UmsTask();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime test = LocalDateTime.of(currentDate, currentTime);

        Instant testBase = Instant.now();

        umsTask.setId((long) 100);
        umsTask.setProcess_instance_id("test-uuid");
        umsTask.setName("test task");
        umsTask.setRequester("requester");
        umsTask.setAssignee("assignee");
        umsTask.setPriority(Priority.HIGH);
        umsTask.setDueDate(test);
        umsTask.setReceivedDate(test);
        umsTask.setLoe(Loe.ONE);
        umsTask.setStatus(Status.IN_PROGRESS);
        umsTask.setDescription("Test task for use.");
        umsTask.setCreatedDate(testBase);
        umsTask.setUpdatedDate(testBase);

        assertInstanceOf(Long.class, umsTask.getId());
        assertInstanceOf(String.class, umsTask.getProcess_instance_id());
        assertInstanceOf(String.class, umsTask.getName());
        assertInstanceOf(String.class, umsTask.getRequester());
        assertInstanceOf(String.class, umsTask.getAssignee());
        assertInstanceOf(Priority.class, umsTask.getPriority());
        assertInstanceOf(LocalDateTime.class, umsTask.getDueDate());
        assertInstanceOf(LocalDateTime.class, umsTask.getReceivedDate());
        assertInstanceOf(Loe.class, umsTask.getLoe());
        assertInstanceOf(Status.class, umsTask.getStatus());
        assertInstanceOf(String.class, umsTask.getDescription());
        assertInstanceOf(Instant.class, umsTask.getCreatedDate());
        assertInstanceOf(Instant.class, umsTask.getUpdatedDate());

        assertEquals(100, umsTask.getId());
        assertEquals("test-uuid", umsTask.getProcess_instance_id());
        assertEquals("test task", umsTask.getName());
        assertEquals("requester", umsTask.getRequester());
        assertEquals("assignee", umsTask.getAssignee());
        assertEquals(Priority.HIGH, umsTask.getPriority());
        assertEquals(test, umsTask.getDueDate());
        assertEquals(test, umsTask.getReceivedDate());
        assertEquals(Loe.ONE, umsTask.getLoe());
        assertEquals(Status.IN_PROGRESS, umsTask.getStatus());
        assertEquals("Test task for use.", umsTask.getDescription());
        assertEquals(testBase, umsTask.getCreatedDate());
        assertEquals(testBase, umsTask.getUpdatedDate());
    }
    
    @Test
    void testEquals() {
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime test = LocalDateTime.of(currentDate, currentTime);

        Instant testBase = Instant.now();

        UmsTask umsTask = new UmsTask(
                "test-process",
                "task name",
                "requester",
                "assignee",
                Priority.HIGH,
                test,
                test,
                Loe.ONE,
                Status.IN_PROGRESS,
                "description of task."
        );
        umsTask.setId((long) 20);
        umsTask.setCreatedDate(testBase);
        umsTask.setUpdatedDate(testBase);

        UmsTask umsTask_2 = new UmsTask(
                "test-process",
                "task name",
                "requester",
                "assignee",
                Priority.HIGH,
                test,
                test,
                Loe.ONE,
                Status.IN_PROGRESS,
                "description of task."
        );

        assertFalse(umsTask.equals(umsTask_2));

        umsTask_2.setId(umsTask.getId());
        umsTask_2.setUpdatedDate(umsTask.getUpdatedDate());
        umsTask_2.setCreatedDate(umsTask.getCreatedDate());

        assertTrue(umsTask.equals(umsTask_2));
    }
}