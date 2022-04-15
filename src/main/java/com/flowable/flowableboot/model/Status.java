package com.flowable.flowableboot.model;

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
        for(Status s: values()){
            map.put(s.status, s);
        }
    }

    private Status(String status) {
        this.status = status;
    }

    public String status() {
        return status;
    }

    public static Status valueOfStatus(String status){
        return map.get(status);
    }
}
