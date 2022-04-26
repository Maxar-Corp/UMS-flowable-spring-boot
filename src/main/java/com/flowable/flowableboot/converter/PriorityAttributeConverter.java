package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Priority;

import javax.persistence.AttributeConverter;


public class PriorityAttributeConverter implements AttributeConverter<Priority, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Priority priority) {
        if (priority == null)
            return null;

        switch (priority){
            case FIRST:
                return 1;
            case THIRD:
                return 3;
            case FIFTH:
                return 5;
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
                return Priority.FIRST;
            case 3:
                return Priority.THIRD;
            case 5:
                return Priority.FIFTH;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
