package com.vlasova.exception;

public class ClosePoolException extends Exception {
    public ClosePoolException() {
    }

    public ClosePoolException(String message) {
        super(message);
    }

    public ClosePoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClosePoolException(Throwable cause) {
        super(cause);
    }

    public ClosePoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
