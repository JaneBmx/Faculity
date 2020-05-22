package com.vlasova.command;

import com.vlasova.command.impl.gradereport.EditGradeReportCommand;
import com.vlasova.command.impl.user.*;

public enum CommandType {
    LOGIN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),
    SIGN_UP(new SignUpCommand()),
    EDIT_GRADE_REPORT(new EditGradeReportCommand()),


    SIGN_OUT(new LogOutCommand()),
    GET_ALL_USERS(new GetAllUsersCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
