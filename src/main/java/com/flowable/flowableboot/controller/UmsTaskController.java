package com.flowable.flowableboot.controller;


import com.flowable.flowableboot.dtos.UmsTaskGetDto;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.exception.RecordNotFoundException;
import com.flowable.flowableboot.mappers.MapStructMapper;
import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.service.UmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping(path = "/umsTasks")
public class UmsTaskController {

    private UmsTaskService umsTaskService;

    @Autowired
    public UmsTaskController(UmsTaskService umsTaskService){
        this.umsTaskService = umsTaskService;
    }

    protected UmsTaskController(){}

    @GetMapping
    public ResponseEntity<List<UmsTaskGetDto>> getAllUmsTasks(){
        List<UmsTaskGetDto> list = umsTaskService.getAllUmsTasks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UmsTaskGetDto> updateUmsTask(@PathVariable("id") int id){
        //TODO: Check for the correct way to implement - maybe just provide uri
        UmsTaskGetDto umsTaskGetDto = umsTaskService.getAllUmsTasks().get(id -1);
        if(umsTaskGetDto==null){
            throw new RecordNotFoundException("Invalid UmsTask id: " + id);
        }

        return new ResponseEntity<>(umsTaskGetDto, HttpStatus.OK);
    }

    @GetMapping("search/findByAssignee")
    public ResponseEntity<List<UmsTaskGetDto>> searchByAssignee(@RequestParam String assignee){
        List<UmsTaskGetDto> list = umsTaskService.searchUmsTaskByAssignee(assignee);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("search/findByRequester")
    public ResponseEntity<List<UmsTaskGetDto>> searchByRequester(@RequestParam String requester){
        List<UmsTaskGetDto> list = umsTaskService.searchUmsTaskByRequester(requester);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Void> createUmsTask(@RequestBody @Valid UmsTaskPostDto umsTaskPostDto){
        // TODO: Hook into workflow engine
        // Need to kick off Workflow, return process_id, current task status, and current task Id
        // ProcessInstance pi = runtimeService.createProcessInstanceQuery()
        //        .processInstanceId(process_instance_id).singleResult();

        //TODO: Exception Handling - this could/might check if the process was created above
        if(umsTaskPostDto.getProcess_instance_id().isEmpty()){
            throw new RecordNotFoundException("Invalid process instance with id:  " + umsTaskPostDto.getProcess_instance_id());
        }
        umsTaskService.createUmsTask(umsTaskPostDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UmsTaskGetDto> updateUmsTask(@PathVariable("id") long id,
                                                       @RequestBody @Validated UmsTaskPostDto umsTaskPostDto){
        UmsTaskGetDto updatedUmsTask = umsTaskService.updateUmsTask(id, umsTaskPostDto);
        if(updatedUmsTask==null){
            throw new RecordNotFoundException("Invalid UmsTask id: " + id);
        }

        return new ResponseEntity<>(updatedUmsTask, HttpStatus.OK);
    }

}
