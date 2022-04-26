package com.flowable.flowableboot.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class UmsTaskPostDtoTest {
    @Test
    void umsTaskPostDto() {
        UmsTaskPostDto umsTaskPostDto = new UmsTaskPostDto();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime test = LocalDateTime.of(currentDate, currentTime);

        umsTaskPostDto.setProcess_instance_id("test-uuid");
        umsTaskPostDto.setName("test task");
        umsTaskPostDto.setRequester("requester");
        umsTaskPostDto.setAssignee("assignee");
        umsTaskPostDto.setPriority(3);
        umsTaskPostDto.setDueDate(test);
        umsTaskPostDto.setReceivedDate(test);
        umsTaskPostDto.setLoe("Medium");
        umsTaskPostDto.setStatus("Complete");
        umsTaskPostDto.setDescription("Test task for use.");

        assertInstanceOf(String.class, umsTaskPostDto.getProcess_instance_id());
        assertInstanceOf(String.class, umsTaskPostDto.getName());
        assertInstanceOf(String.class, umsTaskPostDto.getRequester());
        assertInstanceOf(String.class, umsTaskPostDto.getAssignee());
        assertInstanceOf(Integer.class, umsTaskPostDto.getPriority());
        assertInstanceOf(LocalDateTime.class, umsTaskPostDto.getDueDate());
        assertInstanceOf(LocalDateTime.class, umsTaskPostDto.getReceivedDate());
        assertInstanceOf(String.class, umsTaskPostDto.getLoe());
        assertInstanceOf(String.class, umsTaskPostDto.getStatus());
        assertInstanceOf(String.class, umsTaskPostDto.getDescription());

        assertEquals("test-uuid", umsTaskPostDto.getProcess_instance_id());
        assertEquals("test task", umsTaskPostDto.getName());
        assertEquals("requester", umsTaskPostDto.getRequester());
        assertEquals("assignee", umsTaskPostDto.getAssignee());
        assertEquals(3, umsTaskPostDto.getPriority());
        assertEquals(test, umsTaskPostDto.getDueDate());
        assertEquals(test, umsTaskPostDto.getReceivedDate());
        assertEquals("Medium", umsTaskPostDto.getLoe());
        assertEquals("Complete", umsTaskPostDto.getStatus());
        assertEquals("Test task for use.", umsTaskPostDto.getDescription());
    }
}