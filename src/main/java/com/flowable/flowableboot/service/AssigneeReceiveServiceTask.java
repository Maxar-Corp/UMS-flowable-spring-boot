package com.flowable.flowableboot.service;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("assigneeReceiveServiceTask")
@Scope("prototype")
public class AssigneeReceiveServiceTask implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution){
    System.out.println(String.format("Execution status: %s", execution.getVariable("status")));
    System.out.println("Update db with task for assignee");
    execution.setVariable("receivedByAssignee", true);
  }
}
