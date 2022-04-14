package com.flowable.flowableboot.service;

import com.flowable.flowableboot.repository.PersonRepository;
import com.flowable.flowableboot.model.Person;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FlowableService {

  @Autowired
  private RuntimeService runtimeService;

  @Autowired
  private TaskService taskService;

  @Autowired
  private PersonRepository personRepository;

  @Value("${flowable.process.key.id}")
  public String processKey;

  @Value("${flowable.process.defaultStatus}")
  public String defaultStatus;

  //  Method to initiate process with an assignee name
  public void startProcess(String assignee) {

    // Checks person repository for existence of user
    Person person = personRepository.findByUsername(assignee);

    Map<String, Object> variables = new HashMap<String, Object>();
    variables.put("assigner", person.getUsername());
    // On task creation, task status is set to 'Pending'
    variables.put("status", defaultStatus);

    System.out.println(String.format("assigner: %s", variables.get("assigner")));

    runtimeService.startProcessInstanceByKey(processKey, variables);
  }

// Method to get tasks by assignee name
  public List<Task> getTasks(String assignee){
    return taskService.createTaskQuery().taskAssignee(assignee).list();
  }

//  Method to get current processes
  public List<ProcessInstance> getProcessInstances(){

    List<ProcessInstance> processInstances = runtimeService.createProcessInstanceQuery()
        .active()
        .list();
    return processInstances;
  }

  public void processInstanceDetails(String processId){
    System.out.println(String.format("processId: %s", processId));
    // Uncomment to Suspend/remove all active process instances
    runtimeService.suspendProcessInstanceById(processId);
  }

//  Method to add a new user
  public void addUser(String username, String firstName, String lastName){
    Person person = new Person();
    personRepository.save(new Person(username, firstName, lastName));
  }

  public void createDemoUsers(){
    if (personRepository.findAll().size() == 0){
//      personRepository.save(new Person("hokie", "pedro", "sorto", new Date()));
    }
  }

//  This method needs to be passed a process instance ID value to be able
  // to search for specific tasks within the current process
  public Task retrieveTask(String taskId){
    Task task = taskService.createTaskQuery()
        .taskId(taskId)
        .singleResult();
    return task;
  }

  public void completeTask(Task task){
    this.taskService.complete(task.getId());
  }

  public void completeUserTask(Task task, Map<String, String> assign){
    Map<String, Object> variables = new HashMap<>();
    Map<String, Object> taskVars = this.taskService.getVariables(task.getId());

    if(assign.containsKey("assignee") && assign.get("assignee") != ""){
      variables.put("assignee", assign.get("assignee"));
    } else if(assign.containsKey("assigner") && assign.get("assigner") != ""){
      variables.put("assigner", assign.get("assigner"));
    }else{
//      TODO account for NULL values
      variables.put("assigner", taskVars.get("assigner").toString());
      variables.put("assignee", taskVars.get("assignee").toString());
    }
    this.taskService.complete(task.getId(), variables);
  }

  public void setStatus(String status, String taskId){
    Map<String, Object> variables = new HashMap<>();
    if(status == ""){
      variables = this.taskService.getVariables(taskId);
      status = variables.get("status").toString();
    }
    status = status.toLowerCase();
    this.taskService.setVariable(taskId, "status", status);
  }
}
