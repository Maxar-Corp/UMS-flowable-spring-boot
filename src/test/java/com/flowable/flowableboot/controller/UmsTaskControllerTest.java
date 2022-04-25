package com.flowable.flowableboot.controller;

import com.flowable.flowableboot.dto.UmsTaskGetDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UmsTaskControllerTest {

    @Autowired UmsTaskController umsTaskController;

    @Test
    void getAllUmsTasks() {

        ResponseEntity<List<UmsTaskGetDto>> test = umsTaskController.getAllUmsTasks();

        List<UmsTaskGetDto> testList = test.getBody();

        assertEquals(test.getStatusCode(), HttpStatus.OK);
        assertTrue(test.hasBody());
        assertTrue(testList.size() > 0);
    }

    @Test
    void getUmsTask() {
        ResponseEntity<UmsTaskGetDto> test = umsTaskController.getUmsTask(1);
        UmsTaskGetDto umsTest = (UmsTaskGetDto) test.getBody();

        assertEquals(test.getStatusCode(), HttpStatus.OK);
        assertTrue(test.hasBody());
        assertNotNull(test);
        assertTrue(umsTest.getId() == 1);
    }

    @Test
    void searchByAssignee() {
        ResponseEntity<List<UmsTaskGetDto>> test = umsTaskController.searchByAssignee("Rose Bush");

        List<UmsTaskGetDto> testList = test.getBody();

        assertEquals(test.getStatusCode(), HttpStatus.OK);
        assertTrue(test.hasBody());
        assertTrue(testList.size() > 0);
    }

    @Test
    void searchByRequester() {
        ResponseEntity<List<UmsTaskGetDto>> test = umsTaskController.searchByRequester("Simon Sais");

        List<UmsTaskGetDto> testList = test.getBody();

        assertEquals(test.getStatusCode(), HttpStatus.OK);
        assertTrue(test.hasBody());
        assertTrue(testList.size() > 0);
    }


    //TODO: add tests for creating and updating tasks once clear on process flow
    @Test
    void createUmsTask() {

    }

    @Test
    void testUpdateUmsTask() {

    }

}