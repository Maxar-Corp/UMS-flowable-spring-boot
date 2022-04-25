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
            case SECOND:
                return 2;
            case THIRD:
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
                return Priority.FIRST;
            case 2:
                return Priority.SECOND;
            case 3:
                return Priority.THIRD;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
