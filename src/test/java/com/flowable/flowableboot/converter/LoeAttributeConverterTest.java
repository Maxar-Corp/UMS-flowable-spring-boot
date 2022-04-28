package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Loe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoeAttributeConverterTest {

    private LoeAttributeConverter loeAttributeConverter = new LoeAttributeConverter();

    @Test
    void convertToDatabaseColumn() {
        Integer one = loeAttributeConverter.convertToDatabaseColumn(Loe.ONE);
        Integer three = loeAttributeConverter.convertToDatabaseColumn(Loe.THREE);
        Integer five = loeAttributeConverter.convertToDatabaseColumn(Loe.FIVE);

        assertEquals(1, one);
        assertEquals(3, three);
        assertEquals(5, five);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(loeAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Loe one = loeAttributeConverter.convertToEntityAttribute(1);
        Loe three = loeAttributeConverter.convertToEntityAttribute(3);
        Loe five = loeAttributeConverter.convertToEntityAttribute(5);

        assertEquals(Loe.ONE, one);
        assertEquals(Loe.THREE, three);
        assertEquals(Loe.FIVE, five);
    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(loeAttributeConverter.convertToEntityAttribute(null));
    }
}