package com.flowable.flowableboot.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BaseDto {
    @JsonProperty("id")
    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}