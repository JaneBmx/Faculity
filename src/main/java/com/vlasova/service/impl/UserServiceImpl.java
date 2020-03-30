package com.vlasova.service.impl;

import com.vlasova.service.UserService;

public class UserServiceImpl implements UserService {
    private final UserService userService;

    private static class Holder{
        private static final  UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance(){
        return UserServiceImpl.Holder.INSTANCE;
    }

    private UserServiceImpl(){
        userService = UserServiceImpl.getInstance();
    }

    //TODO add methods
}
