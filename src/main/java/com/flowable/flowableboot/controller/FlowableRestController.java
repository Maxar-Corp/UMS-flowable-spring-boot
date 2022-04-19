package com.flowable.flowableboot.controller;
import com.flowable.flowableboot.model.*;
import com.flowable.flowableboot.service.FlowableService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class FlowableRestController {

  private final FlowableService flowableService;

  public FlowableRestController(FlowableService flowableService) {
    this.flowableService = flowableService;
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
  @Operation(summary="Create User")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description="Success", content=@Content)})
  public void CreateUserEndpoint(@RequestBody Person person){
    flowableService.addUser(
        person.getUsername(),
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
    List<TaskRepresentation> taskList = new ArrayList<>();
    for (Task task : tasks) {
      taskList.add(new TaskRepresentation(task.getId(), task.getName()));
    }
    return taskList;
  }

//  Returns all active processes
  @RequestMapping(value="/processes", method= RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
  @Operation(summary="Get All Active Processes")
  @ApiResponses(value = {
      @ApiResponse(responseCode="200", description="Success", content=@Content(mediaType = "application/json",
          array = @ArraySchema(schema = @Schema(implementation = ProcessInstanceRepresentation.class))))})
  public List<ProcessInstanceRepresentation> getProcesses(@RequestParam Boolean delete){
    // Return all process instances
    List<ProcessInstance> processes = flowableService.getProcessInstances();
    // Prints list of process instance ids
    System.out.println("Active process instances: ");
    List<ProcessInstanceRepresentation> processIdList = new ArrayList<>();
    for(ProcessInstance process : processes){
      processIdList.add(new ProcessInstanceRepresentation(process.getId()));
      flowableService.processInstanceDetails(process.getId(), delete);
    }
    return processIdList;
  }
}
