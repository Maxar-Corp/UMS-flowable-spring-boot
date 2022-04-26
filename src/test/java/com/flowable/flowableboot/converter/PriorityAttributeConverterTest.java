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
        Integer third = priorityAttributeConverter.convertToDatabaseColumn(Priority.THIRD);
        Integer fifth = priorityAttributeConverter.convertToDatabaseColumn(Priority.FIFTH);

        assertEquals(1, first);
        assertEquals(3, third);
        assertEquals(5, fifth);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(priorityAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Priority first = priorityAttributeConverter.convertToEntityAttribute(1);
        Priority third = priorityAttributeConverter.convertToEntityAttribute(3);
        Priority fifth = priorityAttributeConverter.convertToEntityAttribute(5);

        assertEquals(Priority.FIRST, first);
        assertEquals(Priority.THIRD, third);
        assertEquals(Priority.FIFTH, fifth);
    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(priorityAttributeConverter.convertToEntityAttribute(null));
    }
}