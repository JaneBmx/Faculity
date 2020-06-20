package com.vlasova.dao.exception.connection;

public class CreatePoolException extends RuntimeException {
    public CreatePoolException() {
    }

    public CreatePoolException(String message) {
        super(message);
    }

    public CreatePoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public CreatePoolException(Throwable cause) {
        super(cause);
    }

    public CreatePoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
