package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Priority;

import javax.persistence.AttributeConverter;


public class PriorityAttributeConverter implements AttributeConverter<Priority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Priority priority) {
        if (priority == null)
            return null;

        switch (priority){
            case LOW:
                return 1;
            case MEDIUM:
                return 2;
            case HIGH:
                return 3;
            default:
                throw new IllegalArgumentException(priority + " not supported.");
        }

    }

    @Override
    public Priority convertToEntityAttribute(Integer integer) {
        if( integer == null)
            return null;

        switch (integer){
            case 1:
                return Priority.LOW;
            case 2:
                return Priority.MEDIUM;
            case 3:
                return Priority.HIGH;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
