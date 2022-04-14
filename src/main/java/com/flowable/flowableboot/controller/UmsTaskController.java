package com.flowable.flowableboot.controller;


import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.service.UmsTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



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
    public Iterable<UmsTask> retrieveAllUmsTasks() {
        return umsTaskService.getAllUmsTasks();
    }

}
