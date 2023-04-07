package org.example.exception;

public enum ErrorCode {
    CANNOT_USE_EMAIL("Cannot use username. Please choose another"),
    INVALID_REQUEST("Please check the information sent in the request"),
    RESOURCE_NOT_FOUND("No resource matching the information sent. Please check your request"),
    UNEXPECTED_ERROR("Something did not go as expected"),
    NOT_AUTHENTICATED("This is a protected resource"),;

    private final String message;

    ErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
