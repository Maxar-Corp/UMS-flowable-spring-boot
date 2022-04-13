package com.flowable.flowableboot.repository;

import com.flowable.flowableboot.model.UmsTask;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UmsTaskRepository extends CrudRepository<UmsTask, Integer> {
    List<UmsTask> findByAssignee (@Param("assignee")String assignee);
    List<UmsTask> findByRequester(@Param("requester") String requester);
}
