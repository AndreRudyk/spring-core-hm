package com.spring.hm.springcorehm.exception;

public class TicketNotFoundException extends ServiceException {
    private static final String DEFAULT_MESSAGE = "Ticket not found";

    public TicketNotFoundException() {
        super(DEFAULT_MESSAGE);
    }

    public TicketNotFoundException(String message) {
        super(message);
    }

    public ErrorType getErrorType() {
        return ErrorType.DATABASE_ERROR_TYPE;
    }
}
