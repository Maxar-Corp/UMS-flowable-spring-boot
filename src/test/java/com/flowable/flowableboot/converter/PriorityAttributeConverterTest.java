package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Priority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriorityAttributeConverterTest {
    private PriorityAttributeConverter priorityAttributeConverter = new PriorityAttributeConverter();

    @Test
    void convertToDatabaseColumn() {
        Integer low = priorityAttributeConverter.convertToDatabaseColumn(Priority.LOW);
        Integer medium = priorityAttributeConverter.convertToDatabaseColumn(Priority.MEDIUM);
        Integer high = priorityAttributeConverter.convertToDatabaseColumn(Priority.HIGH);

        assertEquals(1, low);
        assertEquals(2, medium);
        assertEquals(3, high);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(priorityAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Priority low = priorityAttributeConverter.convertToEntityAttribute(1);
        Priority medium = priorityAttributeConverter.convertToEntityAttribute(2);
        Priority high = priorityAttributeConverter.convertToEntityAttribute(3);

        assertEquals(Priority.LOW, low);
        assertEquals(Priority.MEDIUM, medium);
        assertEquals(Priority.HIGH, high);
    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(priorityAttributeConverter.convertToEntityAttribute(null));
    }
}