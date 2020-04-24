package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.faculty.*;
import com.vlasova.command.impl.user.*;

public enum CommandFactory {
    REGISTRATION(new SignUpCommand()),
    LOG_IN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),
    GET_USERS_BY_ROLE(new GetUsersByRolesCommand()),

    SHOW_ALL_FACULTIES(new GetAllFacultiesCommand()),
    ADD_FACULTY(new AddFacultyCommand()),
    DELETE_FACULTY(new DeleteFacultyCommand()),
    EDIT_FACULTY(new EditFacultyCommand()),
    SHOW_ALL_FREE_PAID_FACULTY(new GetAllFreePaidFacultiesCommand()),
    SHOW_FACULTY_BY_ID(new GetFacultyByIdCommand()),
    SHOW_FACULTY_BY_SUBJECT(new GetFacultiesBySubjectCommand());

    private Command command;

    CommandFactory(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
