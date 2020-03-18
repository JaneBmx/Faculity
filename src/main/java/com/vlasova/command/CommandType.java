package com.vlasova.command;

public enum CommandType {
    //common actions
    LOG_IN,
    LOG_OUT,
    REGISTRATION,
    FACULITY_LIST,

    //abitur action
    ADD_REQUEST,
    DELETE_REQUEST,
    CHECK_REQUEST_STATUS,

    //prepod
    ACCEPT_REQUEST,
    SET_REQUEST_FREE,
    SEE_REQUESTS_LIST
}
