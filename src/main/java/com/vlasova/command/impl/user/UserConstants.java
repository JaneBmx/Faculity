package com.vlasova.command.impl.user;

public abstract class UserConstants {
    static final String USER = "user";
    static final String MSG = "message";
    static final String NAME = "user_name";
    static final String SURNAME = "user_surname";
    static final String EMAIL = "user_email";
    static final String LOGIN = "user_login";
    static final String NEW_PASS = "new_password";
    static final String PASS = "user_password";


    //TODO make RU\EN (internationalize it)
    static final String MSG_SERV_ERR = "Some problems with server. Tra again later.";
    static final String MSG_UPD_DATA_SCC = "Data has been updated";
    static final String MSG_ERR_WRONG_PAS_OR_LOG ="Wrong login or password. Try again";
    static final String MSG_ERR_USER_EXIST = "User with those login and email is already exist";
    static final String MSG_ERR_INV_DATA = "Invalid data";
}
