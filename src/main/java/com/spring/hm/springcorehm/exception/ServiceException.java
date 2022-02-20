package com.spring.hm.springcorehm.exception;

public class ServiceException extends RuntimeException {

    private ErrorType errorType;

    public ServiceException() {
    }

    public ServiceException(String message) {
        super(message);
    }

    public ErrorType getErrorType (){
        return ErrorType.FATAL_ERROR_TYPE;
    }
}
