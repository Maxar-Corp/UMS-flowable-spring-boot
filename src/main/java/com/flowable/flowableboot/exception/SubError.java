package com.flowable.flowableboot.exception;

abstract class SubError {

}


class ApiValidationError extends SubError {
    private String object;
    private String field;
    private Object rejectedValue;
    private String message;

    ApiValidationError(String object, String message) {
        this.object = object;
        this.message = message;
    }
}