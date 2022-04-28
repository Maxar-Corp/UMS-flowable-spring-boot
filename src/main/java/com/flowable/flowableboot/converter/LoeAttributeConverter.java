package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Loe;

import javax.persistence.AttributeConverter;

public class LoeAttributeConverter implements AttributeConverter<Loe, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Loe loe) {
        if(loe == null)
            return null;

        switch (loe){
            case ONE:
                return 1;
            case THREE:
                return 3;
            case FIVE:
                return 5;
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
                return Loe.ONE;
            case 3:
                return Loe.THREE;
            case 5:
                return Loe.FIVE;
            default:
                throw new IllegalArgumentException(integer + " not supported.");
        }
    }
}
