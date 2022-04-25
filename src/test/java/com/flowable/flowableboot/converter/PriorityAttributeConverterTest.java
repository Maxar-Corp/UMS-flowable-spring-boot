package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Priority;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PriorityAttributeConverterTest {
    private PriorityAttributeConverter priorityAttributeConverter = new PriorityAttributeConverter();

    @Test
    void convertToDatabaseColumn() {
        Integer first = priorityAttributeConverter.convertToDatabaseColumn(Priority.FIRST);
        Integer second = priorityAttributeConverter.convertToDatabaseColumn(Priority.SECOND);
        Integer third = priorityAttributeConverter.convertToDatabaseColumn(Priority.THIRD);

        assertEquals(1, first);
        assertEquals(2, second);
        assertEquals(3, third);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(priorityAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Priority high = priorityAttributeConverter.convertToEntityAttribute(1);
        Priority medium = priorityAttributeConverter.convertToEntityAttribute(2);
        Priority low = priorityAttributeConverter.convertToEntityAttribute(3);

        assertEquals(Priority.FIRST, high);
        assertEquals(Priority.SECOND, medium);
        assertEquals(Priority.THIRD, low);
    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(priorityAttributeConverter.convertToEntityAttribute(null));
    }
}