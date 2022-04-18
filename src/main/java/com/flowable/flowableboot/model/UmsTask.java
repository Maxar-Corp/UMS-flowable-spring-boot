package com.flowable.flowableboot.model;


import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Convert(converter = PriorityAttributeConverter.class)
    @Column
    private Priority priority;

    @Column
    private LocalDateTime dueDate;

    @Column
    private LocalDateTime receivedDate;

    @Convert(converter = LoeAttributeConverter.class)
    @Column
    private Loe loe;

    @Convert(converter = StatusAttributeConverter.class)
    @Column
    private Status status;

    @Column(length = 2000)
    private String description;

    public UmsTask(String process_instance_id, String name, String requester, String assignee, Priority priority,
                   LocalDateTime dueDate, LocalDateTime receivedDate, Loe loe, Status  status, String description){
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
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

    public Loe getLoe() {
        return loe;
    }

    public void setLoe(Loe loe) {
        this.loe = loe;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

        return "umsTask{" +
                "id=" + this.getId() +
                ", process_instance_id='" + process_instance_id + '\'' +
                ", name='" + name + '\'' +
                ", requester='" + requester + '\'' +
                ", assignee=" + assignee +
                ", priority='" + priority.getValue() + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", receivedDate='" + receivedDate + '\'' +
                ", loe=" + loe.loe() +
                ", status=" + status.status() +
                ", description=" + description +
                ", createdDate=" + this.getCreatedDate() +
                ", updatedDate=" + this.getUpdatedDate() +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        UmsTask task = (UmsTask) obj;
        return Objects.equals(this.getId(), task.getId()) &&
                Objects.equals(process_instance_id, task.process_instance_id) &&
                Objects.equals(requester, task.requester) &&
                Objects.equals(assignee, task.assignee) &&
                priority== task.priority &&
                Objects.equals(dueDate, task.dueDate) &&
                Objects.equals(receivedDate, task.receivedDate) &&
                loe == task.loe &&
                status == status &&
                Objects.equals(description, task.description) &&
                Objects.equals(createdDate, task.createdDate) &&
                Objects.equals(updatedDate, task.updatedDate);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getId(), process_instance_id, requester, assignee, priority,
                dueDate, receivedDate, loe, status, description, createdDate, updatedDate);
    }
}
