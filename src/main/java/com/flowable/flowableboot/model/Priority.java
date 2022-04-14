package com.flowable.flowableboot.model;

public enum Priority {
    HIGH(1), MEDIUM(2), LOW(3);

    private final int value;

    Priority(final int newValue){
        value = newValue;
    }

    public int getValue() {
        return value;
    }
}
