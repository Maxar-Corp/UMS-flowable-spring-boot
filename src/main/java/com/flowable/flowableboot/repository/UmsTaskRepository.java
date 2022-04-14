package com.flowable.flowableboot.repository;

import com.flowable.flowableboot.model.UmsTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

public interface UmsTaskRepository extends CrudRepository<UmsTask, Long> {
    List<UmsTask> findByAssignee (String assignee);
    List<UmsTask> findByRequester(String requester);
}
