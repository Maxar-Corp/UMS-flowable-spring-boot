package com.flowable.flowableboot.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

public enum Priority {
    FIRST(1), SECOND(2), THIRD(3);

    private final int value;
    private static Map map = new HashMap<>();

    private Priority(int value){
        this.value = value;
    }

    static{
        for(Priority priority: Priority.values()){
            map.put(priority.value, priority);
        }
    }

    public static Priority valueOf(int priority){
        return (Priority) map.get(priority);
    }

    public int getValue(){
        return value;
    }
}
