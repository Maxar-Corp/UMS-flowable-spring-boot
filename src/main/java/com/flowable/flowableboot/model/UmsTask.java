package com.flowable.flowableboot.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
public class UmsTask extends BaseEntity{

    @Column
    private String process_instance_id;

    @Column
    private String name;

    @Column
    private String requester;

    @Column
    private String assignee;

    @Column
    private Integer priority;

    @Column
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime receivedDate;

    @Column
    private String loe;

    @Column
    private String status;

    @Column(length = 2000)
    private String description;

    public UmsTask(String process_instance_id, String name, String requester, String assignee, Integer priority,
                   LocalDateTime dueDate, LocalDateTime receivedDate, String loe, String  status, String description){
        super();
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

    protected UmsTask(){}

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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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

    public String getLoe() {
        return loe;
    }

    public void setLoe(String loe) {
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

    @Override
    public String toString(){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");

        return "umsTask{" +
                "id=" + this.getId() +
                ", process_instance_id='" + process_instance_id + '\'' +
                ", name='" + name + '\'' +
                ", requester='" + requester + '\'' +
                ", assignee=" + assignee +
                ", priority='" + priority + '\'' +
                ", dueDate='" + dueDate.format(dateTimeFormatter) + '\'' +
                ", receivedDate='" + receivedDate.format(dateTimeFormatter) + '\'' +
                ", loe=" + loe +
                ", status=" + status +
                ", description=" + description +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UmsTask task = (UmsTask) obj;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");
        return Objects.equals(this.getId(), task.getId()) &&
                Objects.equals(process_instance_id, task.process_instance_id) &&
                Objects.equals(requester, task.requester) &&
                Objects.equals(assignee, task.assignee) &&
                Objects.equals(priority, task.priority) &&
                Objects.equals(dueDate.format(dateTimeFormatter), task.dueDate.format(dateTimeFormatter)) &&
                Objects.equals(receivedDate.format(dateTimeFormatter), task.receivedDate.format(dateTimeFormatter)) &&
                Objects.equals(loe, task.loe) &&
                Objects.equals(status, task.status) &&
                Objects.equals(description, task.description);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getId(), process_instance_id, requester, assignee, priority,
                dueDate, receivedDate, loe, status, description);
    }
}
