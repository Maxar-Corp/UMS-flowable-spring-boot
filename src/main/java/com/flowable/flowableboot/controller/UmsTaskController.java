package com.flowable.flowableboot.controller;


import com.flowable.flowableboot.dto.UmsTaskGetDto;
import com.flowable.flowableboot.dto.UmsTaskPostDto;
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

    public UmsTaskController(){}

    @GetMapping
    public ResponseEntity<List<UmsTaskGetDto>> getAllUmsTasks(){
        List<UmsTaskGetDto> list = umsTaskService.getAllUmsTasks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UmsTaskGetDto> getUmsTask(@PathVariable("id") int id){
        //TODO: Check for the correct way to implement - maybe just provide uri
        UmsTaskGetDto umsTaskGetDto = umsTaskService.getAllUmsTasks().get(id -1);
        if(umsTaskGetDto==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        if(umsTaskService.createUmsTask(umsTaskPostDto) == null){
            throw new RuntimeException("UmsTask was not created.");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UmsTaskGetDto> updateUmsTask(@PathVariable("id") long id,
                                                       @RequestBody @Validated UmsTaskPostDto umsTaskPostDto){
        UmsTaskGetDto updatedUmsTask = umsTaskService.updateUmsTask(id, umsTaskPostDto);
        if(updatedUmsTask==null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }

        return new ResponseEntity<>(updatedUmsTask, HttpStatus.OK);
    }

}
