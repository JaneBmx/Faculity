package com.vlasova.command;

public abstract class RequestParams {
    /**Common constants*/
    public static final String USERS            = "users";
    public static final String COMMAND          = "command";
    public static final String TYPE             = "type";

    /**User constants*/
    public static final String USER             = "user";
    public static final String USER_NAME        = "user_name";
    public static final String SURNAME          = "user_surname";
    public static final String LOGIN            = "user_login";
    public static final String NEW_PASS         = "new_password";
    public static final String PASS             = "user_password";
    public static final String USER_ID          = "user_id";

    /**GradeReport constants*/
    public static final String GRADE_REPORT     = "grade_report";
    public static final String GRADE_REPORT_ID  = "grade_report_id";

    /**Faculty constants*/
    public static final String FACULTY          = "faculty";
    public static final String FACULTY_ID       = "faculty_id";

    /**Req constants*/
    public static final String USER_LIST        = "user_list";
    public static final String FACULTY_LIST     = "faculty_list";
    public static final String GR_LIST          = "grade_report_list";
    public static final String COMMON_INFO      = "common_info";

    /**Session params*/
    public static final String ROLE             = "role";
    public static final String ADMIN            = "admin";
    public static final String GUEST            = "guest";

    /**Messages*/
    public static final String MSG              = "message";
    public static final String MSG_ADD_FACULTY  = "message_add_faculty";
    public static final String MSG_SIGNUP       = "message_signup";
    public static final String MSG_EDIT_INFO = "message_edit_info";
    public static final String MSG_SERV_ERR     = "Some problems with server. Tra again later.";
    public static final String MSG_UPD_DATA_SCC = "Data has been updated";
    public static final String MSG_WRONG_LOG_IN = "Wrong login or password. Try again";
    public static final String MSG_USER_EXIST   = "User with those login and email is already exist";
    public static final String MSG_ERR_INV_DATA = "Invalid data";

    private RequestParams(){}
}