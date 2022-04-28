package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Status;

import javax.persistence.AttributeConverter;

public class StatusAttributeConverter implements AttributeConverter<Status, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Status status) {
        if(status ==null)
            return null;
        switch (status){
            case PENDING:
                return 1;
            case TO_REVIEW:
                return 2;
            case IN_PROGRESS:
                return 3;
            case COMPLETE:
                return 4;
            default:
                throw new IllegalArgumentException(status + " not supported.");
        }
    }

    @Override
    public Status convertToEntityAttribute(Integer integer) {
        if(integer == null)
            return null;
        switch (integer){
            case 1:
                return Status.PENDING;
            case 2:
                return Status.TO_REVIEW;
            case 3:
                return Status.IN_PROGRESS;
            case 4:
                return Status.COMPLETE;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
