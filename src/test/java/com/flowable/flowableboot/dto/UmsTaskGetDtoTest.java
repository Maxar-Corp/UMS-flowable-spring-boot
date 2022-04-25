package com.flowable.flowableboot.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class UmsTaskGetDtoTest {

    @Test
    void umsTaskGetDto(){

        UmsTaskGetDto umsTaskGetDto = new UmsTaskGetDto();
        LocalDate currentDate = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime test = LocalDateTime.of(currentDate, currentTime);

        umsTaskGetDto.setId(100);
        umsTaskGetDto.setProcess_instance_id("test-uuid");
        umsTaskGetDto.setName("test task");
        umsTaskGetDto.setRequester("requester");
        umsTaskGetDto.setAssignee("assignee");
        umsTaskGetDto.setPriority(2);
        umsTaskGetDto.setDueDate(test);
        umsTaskGetDto.setReceivedDate(test);
        umsTaskGetDto.setLoe("Medium");
        umsTaskGetDto.setStatus("Complete");
        umsTaskGetDto.setDescription("Test task for use.");

        assertInstanceOf(Long.class, umsTaskGetDto.getId());
        assertInstanceOf(String.class, umsTaskGetDto.getProcess_instance_id());
        assertInstanceOf(String.class, umsTaskGetDto.getName());
        assertInstanceOf(String.class, umsTaskGetDto.getRequester());
        assertInstanceOf(String.class, umsTaskGetDto.getAssignee());
        assertInstanceOf(Integer.class, umsTaskGetDto.getPriority());
        assertInstanceOf(LocalDateTime.class, umsTaskGetDto.getDueDate());
        assertInstanceOf(LocalDateTime.class, umsTaskGetDto.getReceivedDate());
        assertInstanceOf(String.class, umsTaskGetDto.getLoe());
        assertInstanceOf(String.class, umsTaskGetDto.getStatus());
        assertInstanceOf(String.class, umsTaskGetDto.getDescription());

        assertEquals(100, umsTaskGetDto.getId());
        assertEquals("test-uuid", umsTaskGetDto.getProcess_instance_id());
        assertEquals("test task", umsTaskGetDto.getName());
        assertEquals("requester", umsTaskGetDto.getRequester());
        assertEquals("assignee", umsTaskGetDto.getAssignee());
        assertEquals(2, umsTaskGetDto.getPriority());
        assertEquals(test, umsTaskGetDto.getDueDate());
        assertEquals(test, umsTaskGetDto.getReceivedDate());
        assertEquals("Medium", umsTaskGetDto.getLoe());
        assertEquals("Complete", umsTaskGetDto.getStatus());
        assertEquals("Test task for use.", umsTaskGetDto.getDescription());
    }

}