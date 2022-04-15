package com.flowable.flowableboot.controller;
import com.flowable.flowableboot.model.Person;
import com.flowable.flowableboot.model.ProcessInstanceRepresentation;
import com.flowable.flowableboot.model.StartProcessRepresentation;
import com.flowable.flowableboot.model.TaskRepresentation;
import com.flowable.flowableboot.model.UserTaskRepresentation;
import com.flowable.flowableboot.service.FlowableService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FlowableRestController {

  private final FlowableService flowableService;

  public FlowableRestController(FlowableService flowableService) {
    this.flowableService = flowableService;
  }

//  Endpoint used to start a process instance
  @PostMapping(value="/process")
  public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation){
    String assignee = startProcessRepresentation.getAssignee();
    flowableService.startProcess(assignee);
  }

//  Endpoint used to complete a user task
  @PostMapping(value="/usertask")
  public void completeUserTask(@RequestBody UserTaskRepresentation userTaskRepresentation){
    String taskId = userTaskRepresentation.getId();
    String taskStatus = userTaskRepresentation.getStatus();
    Map<String,String> taskAssign = userTaskRepresentation.getAssign();
    
    Task task = flowableService.retrieveTask(taskId);
    flowableService.setStatus(taskStatus, taskId);
    flowableService.completeUserTask(task, taskAssign);
  }

//  Endpoint to create a user task
  @PostMapping(value="/user")
  public void CreateUserEndpoint(@RequestBody Person person){
    flowableService.addUser(
        person.getUsername(),
        person.getFirstName(),
        person.getLastName());
  }

//  Endpoint used to retrieve task information
  @RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
    //  This is retrieving tasks by assignee name
    List<Task> tasks = flowableService.getTasks(assignee);
    List<TaskRepresentation> taskList = new ArrayList<>();
    for (Task task : tasks) {
      taskList.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return taskList;
  }

//  Returns all active processes
  @RequestMapping(value="/processes", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  public List<ProcessInstanceRepresentation> getProcesses(){
    // Return all process instances
    List<ProcessInstance> processes = flowableService.getProcessInstances();
    // Prints list of process instance ids
    System.out.println("Active process instances: ");
    List<ProcessInstanceRepresentation> processIdList = new ArrayList<>();
    for(ProcessInstance process : processes){
      processIdList.add(new ProcessInstanceRepresentation(process.getId()));
      flowableService.processInstanceDetails(process.getId());
    }
    return processIdList;
  }
}
