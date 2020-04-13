package com.vlasova.exception.service;

public class UserDataNotValidException extends ServiceException {
    public UserDataNotValidException() {
        super("User data is not valid");
    }
}
