package com.vlasova.command;

import com.vlasova.command.impl.ajax.GetAllFacultiesAJAX;
import com.vlasova.command.impl.ajax.GetAllGradeReportsAJAX;
import com.vlasova.command.impl.faculty.AddFacultyCommand;
import com.vlasova.command.impl.gradereport.EditGradeReportCommand;
import com.vlasova.command.impl.ajax.GetAllUserAJAX;
import com.vlasova.command.impl.page.ProfilePageCommand;
import com.vlasova.command.impl.user.*;

public enum CommandType {
    /*user commands*/
    LOGIN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),
    SIGN_UP(new SignUpCommand()),
    EDIT_REQUEST(new EditGradeReportCommand()),
    LOG_OUT(new LogOutCommand()),

    /*Footer links*/
    PROFILE(new ProfilePageCommand()),

    /*ADMIN*/
    ADD_FACULTY(new AddFacultyCommand()),
    DELETE_USER(new DeleteUserCommand()),
    GET_ALL_USERS(new GetAllUsersCommand()),


    /*testing*/
    GET_ALL_USERS_AJAX(new GetAllUserAJAX()),
    GET_ALL_FACULTIES_AJAX(new GetAllFacultiesAJAX()),
    GET_ALL_GRADE_REPORTS_AJAX(new GetAllGradeReportsAJAX());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
