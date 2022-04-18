package com.flowable.flowableboot;

import static org.awaitility.Awaitility.await;

import com.flowable.flowableboot.model.Person;
import com.flowable.flowableboot.repository.PersonRepository;
import com.flowable.flowableboot.service.FlowableService;
import java.util.concurrent.TimeUnit;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class FlowableUserTaskTests {
  @Autowired
  private RuntimeService runtimeService;
  @Autowired
  private TaskService taskService;
  @Autowired
  private HistoryService historyService;
  @Autowired
  private PersonRepository personRepository;
  @Autowired
  private FlowableService flowableService;

  @Test
  @Deployment(resources = "/processes/ums_simple_happy_path.bpmn20.xml")
  void testUserTaskWorkflow(){

//    personRepository.save(new Person("assignee", "f_assign", "l_assign"));
//    personRepository.save(new Person("requester", "f_req", "l_req"));

    // Pull a person from personRepository
    Person requester = personRepository.findByUsername("requester");

    ProcessInstance processInstance = flowableService.startProcess(requester.getUsername());

    // Check if triggerable custom service task was reached
    await().atMost(30L, TimeUnit.SECONDS).until(
        () -> this.runtimeService.createExecutionQuery()
            .activityId("requesterAssignTask")  
            .processInstanceId(processInstance.getProcessInstanceId())
            .singleResult() != null
    );
  }

}
