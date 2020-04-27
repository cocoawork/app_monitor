package com.cocoawork.appstore.exception;

public class UserException extends CustomException {

    public UserException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum);
    }
}
