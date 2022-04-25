package com.flowable.flowableboot.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.dto.UmsTaskPostDto;
import com.flowable.flowableboot.mappers.MapStructMapper;
import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.repository.UmsTaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.nio.file.Paths;
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

        verify(umsTaskRepository, times(1)).save(any(UmsTask.class));
        verify(mapStructMapper, times(1)).umsTaskPostDtoToUmsTask(any(UmsTaskPostDto.class));
        assertNotNull(test);
    }

    @Test
    public void updateUmsTask(){
        lenient().when(mapStructMapper.umsTaskToUmsTaskGetDto(any(UmsTask.class))).thenReturn(umsTaskGetDtoList.get(0));
        lenient().when(umsTaskRepository.save(any(UmsTask.class))).thenReturn(umsTaskList.get(0));
        lenient().when(umsTaskRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(umsTaskList.get(0)));

        long testId = 1;

        UmsTaskGetDto test = umsTaskService.updateUmsTask(testId, umsTaskPostDtos.get(0));

        verify(umsTaskRepository, times(1)).findById(anyLong());
        verify(umsTaskRepository, times(1)).save(any(UmsTask.class));
        verify(mapStructMapper, times(1)).umsTaskToUmsTaskGetDto(any(UmsTask.class));
        assertNotNull(test);
    }

    @Test
    public void getAllUmsTasks(){

        lenient().when(mapStructMapper.umsTaskListToUmsTaskGetDto(any(Iterable.class))).thenReturn(umsTaskGetDtoList);

        lenient().when(umsTaskRepository.findAll()).thenReturn(umsTaskList);

        List<UmsTaskGetDto>  test = umsTaskService.getAllUmsTasks();

        verify(umsTaskRepository, times(1)).findAll();
        verify(mapStructMapper, times(1)).umsTaskListToUmsTaskGetDto(any(Iterable.class));
        assertNotNull(test);
        assertEquals(4, test.size());
    }

    @Test
    public void searchUmsTaskByAssignee(){

        lenient().when(mapStructMapper.umsTaskListToUmsTaskGetDto(any(Iterable.class))).thenReturn(umsTaskGetDtoList);

        lenient().when(umsTaskRepository.findByAssignee(anyString())).thenReturn(umsTaskList);

        List<UmsTaskGetDto>  test = umsTaskService.searchUmsTaskByAssignee("Test");

        verify(umsTaskRepository, times(1)).findByAssignee(anyString());
        verify(mapStructMapper, times(1)).umsTaskListToUmsTaskGetDto(any(Iterable.class));
        assertNotNull(test);
        assertEquals(4, test.size());
    }

    @Test
    public void searchUmsTaskByRequester(){
        lenient().when(mapStructMapper.umsTaskListToUmsTaskGetDto(any(Iterable.class))).thenReturn(umsTaskGetDtoList);

        lenient().when(umsTaskRepository.findByRequester(anyString())).thenReturn(umsTaskList);

        List<UmsTaskGetDto>  test = umsTaskService.searchUmsTaskByRequester("Test");

        verify(umsTaskRepository, times(1)).findByRequester(anyString());
        verify(mapStructMapper, times(1)).umsTaskListToUmsTaskGetDto(any(Iterable.class));
        assertNotNull(test);
        assertEquals(4, test.size());
    }

    @Test
    public void totalTest(){

        long total = umsTaskService.total();

        verify(umsTaskRepository, times(1)).count();
        assertNotNull(total);
    }

    private ObjectMapper getUMSObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

}
