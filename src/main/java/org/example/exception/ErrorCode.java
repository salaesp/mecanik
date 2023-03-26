package org.example.exception;

public enum ErrorCode {
    CANNOT_USE_EMAIL("Cannot use username. Please choose another"),
    RESOURCE_NOT_FOUND("No resource matching the information sent. Please check your request");;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
