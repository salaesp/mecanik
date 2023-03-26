package org.example.controller.response;

import lombok.Builder;
import org.example.exception.ErrorCode;

@Builder
public class ErrorResponse {
    private ErrorCode code;

    public String getCode() {
        return this.code.name();
    }

    public String getMessage() {
        return this.code.getMessage();
    }
}
