package com.flowable.flowableboot.service;

import com.flowable.flowableboot.dtos.UmsTaskGetDto;
import com.flowable.flowableboot.dtos.UmsTaskPostDto;
import com.flowable.flowableboot.mappers.MapStructMapper;
import com.flowable.flowableboot.model.Loe;
import com.flowable.flowableboot.model.Priority;
import com.flowable.flowableboot.model.Status;
import com.flowable.flowableboot.model.UmsTask;
import com.flowable.flowableboot.repository.UmsTaskRepository;
import org.flowable.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class UmsTaskService {
    private UmsTaskRepository umsTaskRepository;
    private MapStructMapper mapStructMapper;

    @Autowired
    public UmsTaskService(MapStructMapper mapStructMapper, UmsTaskRepository umsTaskRepository){
        this.mapStructMapper = mapStructMapper;
        this.umsTaskRepository = umsTaskRepository;
    }

    /**
     * Create a new UmsTask - Ideally, this would assume a process was started in workflow engine
     *
     * @param umsTaskPostDto umsTaskPostDto
     *
     * @return No return
     *
     * @exception RuntimeException if associated process id is not verified
     */
    public UmsTask createUmsTask(UmsTaskPostDto umsTaskPostDto){
              return umsTaskRepository.save(mapStructMapper.umsTaskPostDtoToUmsTask(umsTaskPostDto));
    }

    public UmsTaskGetDto updateUmsTask(Long id, UmsTaskPostDto umsTaskPostDto){
        UmsTask existingUmsTask = umsTaskRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Did not find UmsTask with id = " + id));

        existingUmsTask.setProcess_instance_id(umsTaskPostDto.getProcess_instance_id());
        existingUmsTask.setName(umsTaskPostDto.getName());
        existingUmsTask.setRequester(umsTaskPostDto.getRequester());
        existingUmsTask.setAssignee(umsTaskPostDto.getAssignee());
        existingUmsTask.setPriority(Priority.valueOf(umsTaskPostDto.getPriority()));
        existingUmsTask.setDueDate(umsTaskPostDto.getDueDate());
        existingUmsTask.setReceivedDate(umsTaskPostDto.getReceivedDate());
        existingUmsTask.setLoe(Loe.valueOfLoe(umsTaskPostDto.getLoe()));
        existingUmsTask.setStatus(Status.valueOfStatus(umsTaskPostDto.getStatus()));
        existingUmsTask.setDescription(umsTaskPostDto.getDescription());
        existingUmsTask.setUpdatedDate(Instant.now());

        UmsTaskGetDto updatedUmsTask = mapStructMapper.umsTaskToUmsTaskGetDto(umsTaskRepository.save(existingUmsTask));

        return updatedUmsTask;
    }

    /**
     * Return Iterahble of all UmsTasks
     * @return all UmsTasks
     */
    public List<UmsTaskGetDto> getAllUmsTasks(){
        List<UmsTaskGetDto> list = new ArrayList<UmsTaskGetDto>();
        for (UmsTask umsTask : umsTaskRepository.findAll()) {
            list.add(mapStructMapper.umsTaskToUmsTaskGetDto(umsTask));
        }

        return list;
    }

    public List<UmsTaskGetDto> searchUmsTaskByAssignee(String assignee){
        List<UmsTaskGetDto> list = new ArrayList<UmsTaskGetDto>();
        for (UmsTask umsTask : umsTaskRepository.findByAssignee(assignee)) {
            list.add(mapStructMapper.umsTaskToUmsTaskGetDto(umsTask));
        }

        return list;
    }

    public List<UmsTaskGetDto> searchUmsTaskByRequester(String requester){
        List<UmsTaskGetDto> list = new ArrayList<UmsTaskGetDto>();
        for (UmsTask umsTask : umsTaskRepository.findByRequester(requester)) {
            list.add(mapStructMapper.umsTaskToUmsTaskGetDto(umsTask));
        }

        return list;
    }

    /**
     * Return number of UmsTasks
     */
    public long total(){ return umsTaskRepository.count();}
}
