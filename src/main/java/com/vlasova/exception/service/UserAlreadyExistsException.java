package com.vlasova.exception.service;

public class UserAlreadyExistsException extends ServiceException {

    public UserAlreadyExistsException(String email) {
        super("User with email: "+ email+ " already exists");
    }
}
