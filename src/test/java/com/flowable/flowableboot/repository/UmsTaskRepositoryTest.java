package com.flowable.flowableboot.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.model.UmsTask;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UmsTaskRepositoryTest {

    @Autowired
    private UmsTaskRepository umsTaskRepository;

    private UmsTask umsTask;

    private ObjectMapper objectMapper = getUMSObjectMapper();
    private List<UmsTask> umsTaskList = Arrays.asList(objectMapper.readValue(Paths
            .get("src/test/resources/UmsTaskList.json").toFile(), UmsTask[].class));;

    UmsTaskRepositoryTest() throws IOException, IOException {
    }


    @BeforeEach
    public void setup(){
        umsTask = umsTaskList.get(0);
    }

    @AfterEach
    public void teardown(){
        umsTask = null;
    }

    @Test
    void saveUmsTask(){
        umsTaskRepository.save(umsTask);
        UmsTask retrieved = umsTaskRepository.findById(umsTask.getId()).get();

        assertEquals(umsTask.getId(), retrieved.getId());
    }

    @Test
    void findAllUmsTasks(){
        umsTaskList.stream().forEach((task) -> umsTaskRepository.save(task));

        List<UmsTask> retrievedTasks = (List<UmsTask>) umsTaskRepository.findAll();

        assertNotNull(retrievedTasks);
        assertTrue(umsTaskList.size() <= retrievedTasks.size());
    }

    @Test
    void findByAssignee() {
        List<UmsTask> curated_list = new ArrayList<>();
        umsTaskList.forEach((temp) -> {
            temp.setAssignee("tester-1");
            curated_list.add(temp);
            umsTaskRepository.save(temp);
        });

        List<UmsTask> retrievedTasks = umsTaskRepository.findByAssignee("tester-1");

        assertNotNull(retrievedTasks);
        assertTrue(umsTaskList.size() <= retrievedTasks.size());
        assertEquals("tester-1", retrievedTasks.get(0).getAssignee());
    }

    @Test
    void findByRequester() {
        List<UmsTask> curated_list = new ArrayList<>();
        umsTaskList.forEach((temp) -> {
            temp.setRequester("tester-2");
            curated_list.add(temp);
            umsTaskRepository.save(temp);
        });

        List<UmsTask> retrievedTasks = umsTaskRepository.findByRequester("tester-2");

        assertNotNull(retrievedTasks);
        assertTrue(umsTaskList.size() <= retrievedTasks.size());
        assertEquals("tester-2", retrievedTasks.get(0).getRequester());
    }

    private ObjectMapper getUMSObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        return mapper;
    }

}