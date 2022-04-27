package com.flowable.flowableboot.model;

import liquibase.pro.packaged.O;

import java.util.HashMap;
import java.util.Map;

public enum Status {
    PENDING("Pending"),
    TO_REVIEW("To Review"),
    IN_PROGRESS("In Progress"),
    COMPLETE("Complete");

    public final String status;
    private static final Map<String, Status> map = new HashMap<String, Status>();

    static {
        for(Status s: Status.values()){
            map.put(s.status, s);
        }
    }

    private Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status valueByTitle(String status){
        return map.get(status);
    }

    @Override
    public String toString(){
        return getStatus();
    }
}
