package com.flowable.flowableboot;

import static org.awaitility.Awaitility.await;

import com.flowable.flowableboot.model.Person;
import com.flowable.flowableboot.repository.PersonRepository;
import com.flowable.flowableboot.service.FlowableService;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.Execution;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.test.Deployment;
import org.flowable.task.api.Task;
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

    if(personRepository.findByUsername("requester") == null) {
      personRepository.save(new Person("requester", "f_req", "l_req"));
    }
    if(personRepository.findByUsername("assignee") == null) {
      personRepository.save(new Person("assignee", "f_assign", "l_assign"));
    }

    // Pull a person from personRepository
    Person requester = personRepository.findByUsername("requester");

    ProcessInstance processInstance = flowableService.startProcess(requester.getUsername());

    // Check if requesterAssignTask service task was reached
    await().atMost(30L, TimeUnit.SECONDS).until(
        () -> this.runtimeService.createExecutionQuery()
            .activityId("requesterAssignTask")
            .processInstanceId(processInstance.getProcessInstanceId())
            .singleResult() != null
    );

    // Execute the first user task - requester task
    Task task = this.taskService.createTaskQuery()
        .processInstanceId(processInstance.getProcessInstanceId())
        .taskName("Requester Assigns Task")
        .singleResult();

    String taskId = task.getId();
    String taskStatus = "";
    Map<String,String> taskAssign = new HashMap<>();
    taskAssign.put("assignee","assignee");

    flowableService.setStatus(taskStatus, taskId);
    flowableService.completeUserTask(task, taskAssign);

    personRepository.delete(personRepository.findByUsername("requester"));
    personRepository.delete(personRepository.findByUsername("assignee"));
  }
}
