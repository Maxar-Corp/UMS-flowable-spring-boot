package com.flowable.flowableboot.service;

import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.repository.UmsTaskRepository;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UmsTaskService {
    private UmsTaskRepository umsTaskRepository;
    private RuntimeService runtimeService;

    @Autowired
    public UmsTaskService(RuntimeService runtimeService, UmsTaskRepository umsTaskRepository){
        this.runtimeService = runtimeService;
        this.umsTaskRepository = umsTaskRepository;
    }

    /**
     * Create a new UmsTask - Ideally, this would assume a process was started in workflow engine
     *
     * @param process_instance_id process_instance_id
     * @param name name
     * @param requester requester
     * @param assignee assignee
     * @param priority priority
     * @param dueDate dueDate
     * @param receivedDate receivedDate
     * @param loe loe
     * @param status status
     * @param description description
     *
     * @return UmsTask Entity
     */
    public void createUmsTask(String process_instance_id, String name, String requester, String assignee, Integer priority,
                                 LocalDateTime dueDate, LocalDateTime receivedDate, String loe, String  status, String description){

        // TO DO: Hook into workflow engine
        // ProcessInstance pi = runtimeService.createProcessInstanceQuery()
        //        .processInstanceId(process_instance_id).singleResult();

        if(process_instance_id == null){
            throw new RuntimeException("Process instance did not exist in workflow engine.");
        }

        umsTaskRepository.save(new UmsTask(process_instance_id, name, requester, assignee, priority,
                        dueDate, receivedDate, loe, status, description));
    }

    /**
     * Return Iterahble of all UmsTasks
     * @return all UmsTasks
     */
    public Iterable<UmsTask> getAllUmsTasks(){return umsTaskRepository.findAll();}


    /**
     * Return number of UmsTasks
     */
    public long total(){ return umsTaskRepository.count();}
}
