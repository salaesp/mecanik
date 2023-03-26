package org.example.exception;

public class UserAlreadyExistsException extends CodeException {

    public UserAlreadyExistsException() {
        super(ErrorCode.CANNOT_USE_EMAIL);
    }
}
