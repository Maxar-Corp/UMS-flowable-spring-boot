package com.flowable.flowableboot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import liquibase.pro.packaged.P;

import java.util.HashMap;
import java.util.Map;

public enum Priority {
    LOW ("Low"), MEDIUM("Medium"), HIGH("High");

    public final String priority;
    private static final Map<String, Priority> map = new HashMap<>();

    static{
        for(Priority p: Priority.values()){
            map.put(p.priority, p);
        }
    }
    private Priority(String priority){
        this.priority = priority;
    }
    public static Priority valueByTitle(String priority){
        return map.get(priority);
    }

    public String getPriority(){
        return priority;
    }

    @Override
    public String toString(){
        return getPriority();
    }
}
