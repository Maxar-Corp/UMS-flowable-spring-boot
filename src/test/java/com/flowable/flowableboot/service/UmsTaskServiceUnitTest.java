package com.flowable.flowableboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.dtos.UmsTaskGetDto;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.mappers.MapStructMapper;
import com.flowable.flowableboot.mappers.MapStructMapperImpl;
import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.repository.UmsTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)

public class UmsTaskServiceUnitTest {

    private UmsTaskService umsTaskService;

    @Mock
    private UmsTaskRepository umsTaskRepository;
    @Mock
    private MapStructMapper mapStructMapper;

    private ObjectMapper objectMapper = getUMSObjectMapper();

    private List<UmsTask> umsTaskList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskList.json").toFile(), UmsTask[].class));
    private List<UmsTaskGetDto> umsTaskGetDtoList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskGetDtoList.json").toFile(), UmsTaskGetDto[].class));;
    private List<UmsTaskPostDto> umsTaskPostDtos = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskPostDtoList.json").toFile(), UmsTaskPostDto[].class));;

    public UmsTaskServiceUnitTest() throws IOException {
    }

    @BeforeEach
    void setup(){
        umsTaskService = new UmsTaskService(mapStructMapper, umsTaskRepository);
    }

    @Test
    public void createUmsTask(){
        lenient().when(mapStructMapper.umsTaskPostDtoToUmsTask(any(UmsTaskPostDto.class))).thenReturn(umsTaskList.get(0));
        lenient().when(umsTaskRepository.save(any(UmsTask.class))).thenReturn(umsTaskList.get(0));

        UmsTask test = umsTaskService.createUmsTask(umsTaskPostDtos.get(0));

        verify(umsTaskRepository, atLeastOnce()).save(any(UmsTask.class));
        assertNotNull(test);
        // need test for mapstruct call/mock
    }

    private ObjectMapper getUMSObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

    /**private ArrayList<UmsTask> loadListOfTasks(){
        ArrayList<UmsTask> testList = new ArrayList<>();

        for(UmsTaskPostDto umsTaskPostDto: umsTaskPostDtos){
            testList.add(umsTaskService.createUmsTask(umsTaskPostDto));
        }

        return testList;
    } **/

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
