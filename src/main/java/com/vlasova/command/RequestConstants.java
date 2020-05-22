package com.vlasova.command;

public abstract class RequestConstants {

    /*Common constants*/
    public static final String MSG             = "message";

    /*User constants*/
    public static final String USER            = "user";
    public static final String USER_NAME       = "user_name";
    public static final String SURNAME         = "user_surname";
    public static final String EMAIL           = "user_email";
    public static final String LOGIN           = "user_login";
    public static final String NEW_PASS        = "new_password";
    public static final String PASS            = "user_password";

    /*GradeReport constants*/
    public static final String GRADE_REPORT    = "grade_report";
    public static final String GRADE_REPORT_ID = "grade_report_id";
    public static final String MARK_1          = "mark_1";
    public static final String MARK_2          = "mark_2";
    public static final String MARK_3          = "mark_3";
    public static final String ATTESTAT        = "attestat_mark";
    public static final String PRIVILEGE       = "privilege";

    /*Faculty constants*/
    public static final String FACULTY_ID      = "faculty_id";
    public static final String SUB_1           = "subject_1";
    public static final String SUB_2           = "subject_2";
    public static final String SUB_3           = "subject_3";


    //TODO make RU\EN (internationalize it)
    public static final String MSG_SERV_ERR             = "Some problems with server. Tra again later.";
    public static final String MSG_UPD_DATA_SCC         = "Data has been updated";
    public static final String MSG_ERR_WRONG_PAS_OR_LOG = "Wrong login or password. Try again";
    public static final String MSG_ERR_USER_EXIST       = "User with those login and email is already exist";
    public static final String MSG_ERR_INV_DATA         = "Invalid data";
}
