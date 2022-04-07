package com.flowable.flowableboot.controller;

import com.flowable.flowableboot.model.*;
import com.flowable.flowableboot.service.FlowableService;
import com.flowable.flowableboot.service.ReceiveTask;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
public class FlowableRestController {

  @Autowired
  private final FlowableService flowableService;
  @Autowired
  private final ReceiveTask receiveTask;

  public FlowableRestController(
      FlowableService flowableService, ReceiveTask receiveTask) {
    this.flowableService = flowableService;
    this.receiveTask = receiveTask;
  }

//  Endpoint used to start a process instance
  @PostMapping(value="/process")
  @Operation(summary="Initiate Process Instance")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content)})
  public void startProcessInstance(@RequestBody StartProcessRepresentation startProcessRepresentation){
    String assignee = startProcessRepresentation.getAssignee();
    flowableService.startProcess(assignee);
  }

//  Endpoint used to complete a user task
  @PostMapping(value="/usertask")
  @Operation(summary="Submit Completed User Task")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content)})
  public void runUserTask(@RequestBody TaskRepresentation taskRepresentation){
    String taskId = taskRepresentation.getId();
    String taskName = taskRepresentation.getName();
    // This is retrieving tasks by name and id
    Task task = flowableService.retrieveTask(taskName, taskId);
    flowableService.completeTask(task);
  }

//  Endpoint used to update a task status
  @PostMapping(value="/status")
  @Operation(summary="Update User Task")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content)})
  public void setStatus(@RequestBody TaskStatusRepresentation taskStatusRepresentation){
    String taskId = taskStatusRepresentation.getId();
    String status = taskStatusRepresentation.getStatus();
    flowableService.setStatus(status, taskId);
  }

//  Endpoint to create a user task
  @PostMapping(value="/user")
  @Operation(summary="Create User")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content)})
  public void CreateUserEndpoint(@RequestBody Person person){
    flowableService.addUser(person.getUsername(),
        person.getFirstName(),
        person.getLastName());
  }

//  Endpoint used to retrieve task information
  @RequestMapping(value="/tasks", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary="Retrieve Tasks Assigned to a Specific User")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content(mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = TaskRepresentation.class))))})
  public List<TaskRepresentation> getTasks(@RequestParam String assignee) {
    //  This is retrieving tasks by assignee name
    List<Task> tasks = flowableService.getTasks(assignee);
    List<TaskRepresentation> dtos = new ArrayList<>();
    for (Task task : tasks) {
      dtos.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return dtos;
  }

//  Returns all active processes
  @RequestMapping(value="/processes", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary="Get All Active Processes")
  @ApiResponses(value = {
          @ApiResponse(responseCode="200", description="Success", content=@Content(mediaType = "application/json",
                  array = @ArraySchema(schema = @Schema(implementation = ProcessInstanceRepresentation.class))))})
  public List<ProcessInstanceRepresentation> getProcesses(){
    List<ProcessInstance> processes = flowableService.getProcessInstances();

    System.out.println("Current Processes");
    List<ProcessInstanceRepresentation> processIdList = new ArrayList<>();
    for(ProcessInstance process : processes){
      processIdList.add(new ProcessInstanceRepresentation(process.getId()));
      flowableService.processInstanceDetails(process.getId());
    }
    return processIdList;
  }

}
