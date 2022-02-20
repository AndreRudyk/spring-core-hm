package com.spring.hm.springcorehm.exception;

public class PlaceIsBookedException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "The place is already booked";

    public PlaceIsBookedException() {
        super(DEFAULT_MESSAGE);
    }

    public PlaceIsBookedException(String message) {
        super(message);
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
