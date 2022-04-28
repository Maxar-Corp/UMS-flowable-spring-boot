package com.flowable.flowableboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.dto.UmsTaskPostDto;
import com.flowable.flowableboot.model.UmsTask;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UmsTaskServiceTest {

    @Autowired
    private UmsTaskService umsTaskService;

    private long initial_count;

    private ObjectMapper objectMapper = getUMSObjectMapper();

    private List<UmsTask> umsTaskList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskList.json").toFile(), UmsTask[].class));
    private List<UmsTaskGetDto> umsTaskGetDtoList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskGetDtoList.json").toFile(), UmsTaskGetDto[].class));;
    private List<UmsTaskPostDto> umsTaskPostDtos = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskPostDtoList.json").toFile(), UmsTaskPostDto[].class));;


    UmsTaskServiceTest() throws IOException {
    }


    @BeforeEach
    public void setup(){

        initial_count = umsTaskService.total();
    }

    @Test
    void createUmsTask() {

        UmsTask compare = umsTaskList.get(1);
        UmsTask test = umsTaskService.createUmsTask(umsTaskPostDtos.get(1));

        assertEquals(initial_count + 1, umsTaskService.total());
        assertUmsTasksEqual(compare, test);
    }

    @Test
    void updateUmsTask() {

        UmsTaskPostDto testPost = umsTaskPostDtos.get(0);
        UmsTaskPostDto updatedPost = umsTaskPostDtos.get(1);

        UmsTask test = umsTaskService.createUmsTask(testPost);

        long current_count = umsTaskService.total();
        assertTrue(current_count > initial_count);

        UmsTaskGetDto updated = umsTaskService.updateUmsTask(test.getId(), updatedPost);

        long post_update_count = umsTaskService.total();
        assertEquals(current_count, post_update_count);

        assertEquals(test.getId(), updated.getId());

        //note this is a comparison between a UmsTask and UmsTaskGetDto
        assertNotEquals(test.getProcess_instance_id(), updated.getProcess_instance_id());
        assertNotEquals(test.getDescription(), updated.getDescription());
        assertNotEquals(test.getAssignee(), updated.getAssignee());
        assertNotEquals(test.getRequester(), updated.getRequester());
        assertNotEquals(test.getPriority().getPriority(), updated.getPriority());
        assertNotEquals(test.getStatus().getStatus(), updated.getStatus());
        assertNotEquals(test.getLoe().getValue(), updated.getLoe());
        assertNotEquals(test.getDueDate(), updated.getDueDate());
        assertNotEquals(test.getReceivedDate(), updated.getReceivedDate());

        //comparison between UmsTaskPostDto and UmsTaskGetDto
        assertEquals(updatedPost.getProcess_instance_id(), updated.getProcess_instance_id());
        assertEquals(updatedPost.getDescription(), updated.getDescription());
        assertEquals(updatedPost.getAssignee(), updated.getAssignee());
        assertEquals(updatedPost.getRequester(), updated.getRequester());
        assertEquals(updatedPost.getPriority(), updated.getPriority());
        assertEquals(updatedPost.getStatus(), updated.getStatus());
        assertEquals(updatedPost.getLoe(), updated.getLoe());
        assertEquals(updatedPost.getDueDate(), updated.getDueDate());
        assertEquals(updatedPost.getReceivedDate(), updated.getReceivedDate());
    }

    @Test
    void getAllUmsTasks() {
        List<UmsTask> generatedList = loadListOfTasks();

        long post_count = umsTaskService.total();

        assertEquals(initial_count + generatedList.size(), post_count);

        List<UmsTaskGetDto> getDtos = umsTaskService.getAllUmsTasks();

        assertEquals(post_count, getDtos.size());
    }

    @Test
    void searchUmsTaskByAssignee() {
        List<UmsTask> generatedList = loadListOfTasks();

        List<UmsTaskGetDto> rose_list = umsTaskService.searchUmsTaskByAssignee("Rose Bush");
        List<UmsTaskGetDto> mike_list = umsTaskService.searchUmsTaskByAssignee("Mike Rowe-Soft");
        List<UmsTaskGetDto> non_entity = umsTaskService.searchUmsTaskByAssignee("Non entity");

        assertTrue(rose_list.size() > 0);
        assertTrue(mike_list.size() > 0);
        assertTrue(non_entity.size() == 0);

    }

    @Test
    void searchUmsTaskByRequester() {
        List<UmsTask> generatedList = loadListOfTasks();

        List<UmsTaskGetDto> rose_list = umsTaskService.searchUmsTaskByRequester("Paige Turner");
        List<UmsTaskGetDto> mike_list = umsTaskService.searchUmsTaskByRequester("Simon Sais");
        List<UmsTaskGetDto> non_entity = umsTaskService.searchUmsTaskByRequester("Non entity");

        assertTrue(rose_list.size() > 0);
        assertTrue(mike_list.size() > 0);
        assertTrue(non_entity.size() == 0);
    }

    private ArrayList<UmsTask> loadListOfTasks(){
        ArrayList<UmsTask> testList = new ArrayList<>();

        for(UmsTaskPostDto umsTaskPostDto: umsTaskPostDtos){
            testList.add(umsTaskService.createUmsTask(umsTaskPostDto));
        }

        return testList;
    }

    private ObjectMapper getUMSObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    private void assertUmsTasksEqual(UmsTask reference, UmsTask actual){
        assertEquals(reference.getProcess_instance_id(), actual.getProcess_instance_id());
        assertEquals(reference.getDescription(), actual.getDescription());
        assertEquals(reference.getAssignee(), actual.getAssignee());
        assertEquals(reference.getRequester(), actual.getRequester());
        assertEquals(reference.getPriority(), actual.getPriority());
        assertEquals(reference.getStatus(), actual.getStatus());
        assertEquals(reference.getLoe(), actual.getLoe());
        assertEquals(reference.getDueDate(), actual.getDueDate());
        assertEquals(reference.getReceivedDate(), actual.getReceivedDate());
    }

}