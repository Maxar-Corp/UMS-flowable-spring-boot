package com.flowable.flowableboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class UmsTaskGetDto extends BaseDto {

    @JsonProperty("process_instance_id")
    private String process_instance_id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("requester")
    private String requester;

    @JsonProperty("assignee")
    private String assignee;

    @JsonProperty("priority")
    private String priority;

    @JsonProperty("dueDate")
    private LocalDateTime dueDate;

    @JsonProperty("receivedDate")
    private LocalDateTime receivedDate;

    @JsonProperty("loe")
    private int loe;

    @JsonProperty("status")
    private String status;

    @JsonProperty("description")
    private String description;

    public UmsTaskGetDto() {
    }

    public UmsTaskGetDto(String process_instance_id, String name, String requester, String assignee,
                         String priority, LocalDateTime dueDate, LocalDateTime receivedDate, int loe, String status,
                         String description) {
        this.process_instance_id = process_instance_id;
        this.name = name;
        this.requester = requester;
        this.assignee = assignee;
        this.priority = priority;
        this.dueDate = dueDate;
        this.receivedDate = receivedDate;
        this.loe = loe;
        this.status = status;
        this.description = description;
    }

    public String getProcess_instance_id() {
        return process_instance_id;
    }

    public void setProcess_instance_id(String process_instance_id) {
        this.process_instance_id = process_instance_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRequester() {
        return requester;
    }

    public void setRequester(String requester) {
        this.requester = requester;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDateTime receivedDate) {
        this.receivedDate = receivedDate;
    }

    public int getLoe() {
        return loe;
    }

    public void setLoe(int loe) {
        this.loe = loe;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
