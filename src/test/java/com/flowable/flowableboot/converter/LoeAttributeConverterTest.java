package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Loe;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoeAttributeConverterTest {

    private LoeAttributeConverter loeAttributeConverter = new LoeAttributeConverter();

    @Test
    void convertToDatabaseColumn() {
        Integer high = loeAttributeConverter.convertToDatabaseColumn(Loe.HIGH);
        Integer medium = loeAttributeConverter.convertToDatabaseColumn(Loe.MEDIUM);
        Integer low = loeAttributeConverter.convertToDatabaseColumn(Loe.LOW);

        assertEquals(1, high);
        assertEquals(2, medium);
        assertEquals(3, low);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(loeAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Loe high = loeAttributeConverter.convertToEntityAttribute(1);
        Loe medium = loeAttributeConverter.convertToEntityAttribute(2);
        Loe low = loeAttributeConverter.convertToEntityAttribute(3);

        assertEquals(Loe.HIGH, high);
        assertEquals(Loe.MEDIUM, medium);
        assertEquals(Loe.LOW, low);
    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(loeAttributeConverter.convertToEntityAttribute(null));
    }
}