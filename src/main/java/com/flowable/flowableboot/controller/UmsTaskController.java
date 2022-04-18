package com.flowable.flowableboot.controller;


import com.flowable.flowableboot.dtos.UmsTaskGetDto;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.mappers.MapStructMapper;
import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.service.UmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Void> createUmsTask(@Valid @RequestBody UmsTaskPostDto umsTaskPostDto){
        umsTaskService.createUmsTask(umsTaskPostDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
}
