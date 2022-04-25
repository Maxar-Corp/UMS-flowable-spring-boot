package com.flowable.flowableboot.converter;

import com.flowable.flowableboot.model.Status;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class StatusAttributeConverterTest {

    private StatusAttributeConverter statusAttributeConverter = new StatusAttributeConverter();

    @Test
    void convertToDatabaseColumn() {
        Integer pending = statusAttributeConverter.convertToDatabaseColumn(Status.PENDING);
        Integer to_review = statusAttributeConverter.convertToDatabaseColumn(Status.TO_REVIEW);
        Integer in_progress = statusAttributeConverter.convertToDatabaseColumn(Status.IN_PROGRESS);
        Integer complete= statusAttributeConverter.convertToDatabaseColumn(Status.COMPLETE);

        assertEquals(1, pending);
        assertEquals(2, to_review);
        assertEquals(3, in_progress);
        assertEquals(4, complete);
    }

    @Test
    void convertToDataBaseColumnNull(){
        assertNull(statusAttributeConverter.convertToDatabaseColumn(null));
    }

    @Test
    void convertToEntityAttribute() {
        Status pending = statusAttributeConverter.convertToEntityAttribute(1);
        Status to_review = statusAttributeConverter.convertToEntityAttribute(2);
        Status in_progress = statusAttributeConverter.convertToEntityAttribute(3);
        Status complete = statusAttributeConverter.convertToEntityAttribute(4);

        assertEquals(Status.PENDING, pending);
        assertEquals(Status.TO_REVIEW, to_review);
        assertEquals(Status.IN_PROGRESS, in_progress);
        assertEquals(Status.COMPLETE, complete);

    }

    @Test
    void convertToEntityAttributeNull(){
        assertNull(statusAttributeConverter.convertToEntityAttribute(null));
    }
}