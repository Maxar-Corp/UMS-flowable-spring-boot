package com.flowable.flowableboot.model;

import javax.persistence.AttributeConverter;

public class LoeAttributeConverter implements AttributeConverter<Loe, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Loe loe) {
        if(loe == null)
            return null;

        switch (loe){
            case HIGH:
                return 1;
            case MEDIUM:
                return 2;
            case LOW:
                return 3;
            default:
                throw new IllegalArgumentException(loe + " not supported.");
        }

    }

    @Override
    public Loe convertToEntityAttribute(Integer integer) {
        if(integer == null)
            return null;
        switch (integer){
            case 1:
                return Loe.HIGH;
            case 2:
                return Loe.MEDIUM;
            case 3:
                return Loe.LOW;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
