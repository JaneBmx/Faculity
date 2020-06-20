package com.vlasova.dao.exception.connection;

public class InitiationPoolException extends Exception {
    public InitiationPoolException() {
    }

    public InitiationPoolException(String message) {
        super(message);
    }

    public InitiationPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitiationPoolException(Throwable cause) {
        super(cause);
    }

    public InitiationPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
