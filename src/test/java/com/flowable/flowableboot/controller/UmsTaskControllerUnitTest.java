package com.flowable.flowableboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.FlowablebootApplication;
import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.service.UmsTaskService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = FlowablebootApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
class UmsTaskControllerUnitTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UmsTaskService umsTaskService;


    private ObjectMapper objectMapper = getUMSObjectMapper();
    private List<UmsTaskGetDto> umsTaskGetDtoList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskGetDtoList.json").toFile(), UmsTaskGetDto[].class));;

    UmsTaskControllerUnitTest() throws IOException {
    }

    @Test
    void getAllUmsTasks() throws Exception {
        when(umsTaskService.getAllUmsTasks()).thenReturn(umsTaskGetDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/umsTasks")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void getUmsTask() throws Exception {
        when(umsTaskService.getAllUmsTasks()).thenReturn(umsTaskGetDtoList);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/umsTasks/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)));
    }

    @Test
    void searchByAssignee() throws Exception {
        List<UmsTaskGetDto> curated_list = new ArrayList<>();
                umsTaskGetDtoList.forEach((umsTaskGetDto) -> {
                    umsTaskGetDto.setAssignee("tester-1");
                    curated_list.add(umsTaskGetDto);
                });
        when(umsTaskService.searchUmsTaskByAssignee("tester-1")).thenReturn(curated_list);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/umsTasks/search/findByAssignee?assignee=tester-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void searchByRequester() throws Exception {
        List<UmsTaskGetDto> curated_list = new ArrayList<>();
        umsTaskGetDtoList.forEach((umsTaskGetDto) -> {
            umsTaskGetDto.setRequester("tester-1");
            curated_list.add(umsTaskGetDto);
        });
        when(umsTaskService.searchUmsTaskByRequester("tester-1")).thenReturn(curated_list);

        mockMvc.perform(MockMvcRequestBuilders
                .get("/umsTasks/search/findByRequester?requester=tester-1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    //TODO: unit tests for UmsTask data object - create, update
    @Test
    void createUmsTask() {
    }

    @Test
    void updateUmsTask() {
    }


    private ObjectMapper getUMSObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

}