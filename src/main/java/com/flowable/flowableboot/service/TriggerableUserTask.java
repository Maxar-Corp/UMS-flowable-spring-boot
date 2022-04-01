package com.flowable.flowableboot.service;

import java.util.List;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service("triggerableUserTask")
@Scope("prototype")
public class TriggerableUserTask implements JavaDelegate {

  @Autowired
  private TaskService taskService;

  public void execute( DelegateExecution execution){
    System.out.println("Setting status to complete");
    execution.setVariable("status", "COMPLETE");
  }
}
