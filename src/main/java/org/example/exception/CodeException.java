package org.example.exception;

public class CodeException extends RuntimeException {

    private ErrorCode code;

    public CodeException(ErrorCode code) {
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }
}
