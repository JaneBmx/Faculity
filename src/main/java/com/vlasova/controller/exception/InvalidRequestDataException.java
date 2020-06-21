package com.vlasova.controller.exception;

public class InvalidRequestDataException extends Exception {
    public InvalidRequestDataException() {
    }

    public InvalidRequestDataException(String message) {
        super(message);
    }

    public InvalidRequestDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidRequestDataException(Throwable cause) {
        super(cause);
    }

    public InvalidRequestDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}