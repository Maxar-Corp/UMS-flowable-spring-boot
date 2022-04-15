package com.flowable.flowableboot.utils;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.flowable.flowableboot.model.Loe;
import com.flowable.flowableboot.model.Priority;
import com.flowable.flowableboot.model.Status;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class generateUmsTasks {
    private static ArrayList<String> taskTypes = new ArrayList<String>(){
        {
            add("Report: ");
            add("Presentation: ");
            add("Formal Brief: ");
            add("Respond To Query Topic: ");
        }
    };

    private static ArrayList<String> taskTopics = new ArrayList<String>(){
        {
            add("Company X");
            add("Country Y");
            add("Topic Z");
        }
    };

    // names from https://cybertext.wordpress.com/2007/04/30/fake-names-for-documentation/
    private static ArrayList<String> requesterNames = new ArrayList<String>(){
        {
            add("Simon Sais");
            add("Paige Turner");
            add("Chris Anthemum");
        }
    };

    private static ArrayList<String> assigneeName = new ArrayList<String>(){
        {
            add("Col Fays");
            add("Rose Bush");
            add("Hank R. Cheef");
            add("Mike Rowe-Soft");
            add("Penny Black");
            add("Wiley Waites");
        }
    };

    private static Priority[] priorityList = Priority.values();

    private static ArrayList<Integer> daysDue = new ArrayList<Integer>(){
        {
            add(3); add(5); add(10); add(14);
        }
    };

    private static Loe[] loeList = Loe.values();

    private static Status [] statusList = Status.values();

    private static  ArrayList<String> description = new ArrayList<String>(){
        {
            add("Task requires research and at least three sources.");
            add("Task should include full sources cited for primary resources. \n" +
                    "Important secondary sources should also be provided for review.");
            add("Throughout task completion, keep note of POCs and respective areas of contributions. \n" +
                    "Include name and organization at a minimum.");
        }
    };



    public static void main(String[] args) throws IOException {

        Random rand = new Random();

        File file = new File("./src/main/resources/testUmsTasks.json");

        FileWriter fw = new FileWriter(file, true);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write("[");
        bw.newLine();

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        int loopControl = 25;

        for(int i = 0 ; i < loopControl; i++){
            String taskName = taskTypes.get(rand.nextInt(taskTypes.size()))
                    + taskTopics.get(rand.nextInt(taskTopics.size()));

            LocalDateTime recDate = LocalDateTime.now(ZoneId.of("UTC"))
                    .plusHours(daysDue.get(rand.nextInt(daysDue.size())));
            LocalDateTime dDate = recDate
                    .plusDays(daysDue.get(rand.nextInt(daysDue.size())));

            // This test UMS  object should reflect what the UI would send in a JSON payload.
            // For that reason, the integer or string values are used in this class.
            TestUmsTask test = new TestUmsTask(
                    UUID.randomUUID().toString(),
                    taskName,
                    requesterNames.get(rand.nextInt(requesterNames.size())),
                    assigneeName.get(rand.nextInt(assigneeName.size())),
                    priorityList[rand.nextInt(priorityList.length)].getValue(),
                    dDate,
                    recDate,
                    loeList[rand.nextInt(loeList.length)].loe(),
                    statusList[rand.nextInt(statusList.length)].status(),
                    description.get(rand.nextInt(description.size()))
            );

            String json = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(test);

            if ((i + 1) < loopControl) {
                 json = json + ",";
            }

            bw.write(json);
            bw.newLine();
        }

        bw.write("]");
        bw.newLine();
        bw.close();
    }

    @JsonPropertyOrder({"process_instance_id", "name", "requester", "assignee", "priority", "dueDate",
            "recievedDate", "loe", "status", "description"})
    public static class TestUmsTask{

        private String process_instance_id;
        private String name;
        private String requester;
        private String assignee;
        private Integer priority;
        private LocalDateTime dueDate;
        private LocalDateTime receivedDate;
        private String loe;
        private String status;
        private String description;

        public TestUmsTask(){
            super();
        }
        public TestUmsTask(String process_instance_id, String name, String requester, String assignee,
                           Integer priority, LocalDateTime dueDate, LocalDateTime receivedDate, String loe,
                           String status, String description){
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
    }



}
