package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.faculty.*;
import com.vlasova.command.impl.gradereport.*;
import com.vlasova.command.impl.user.*;

public enum CommandFactory {
    REGISTRATION(new SignUpCommand()),
    LOG_IN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),
    DELETE_USER(new DeleteUser()),
    GET_USERS_BY_ROLE(new GetUsersByRolesCommand()),
    GET_USER_BY_ID(new GetUserById()),

    SHOW_ALL_FACULTIES(new GetAllFacultiesCommand()),
    ADD_FACULTY(new AddFacultyCommand()),
    DELETE_FACULTY(new DeleteFacultyCommand()),
    EDIT_FACULTY(new EditFacultyCommand()),
    SHOW_ALL_FREE_PAID_FACULTY(new GetAllFreePaidFacultiesCommand()),
    SHOW_FACULTY_BY_ID(new GetFacultyByIdCommand()),
    SHOW_FACULTY_BY_SUBJECT(new GetFacultiesBySubjectCommand()),

    ADD_GRADE_REPORT(new AddGradeReportCommand()),
    CHANGE_GRADE_REPORT_STATUS(new ChangeGradeReportStatusCommand()),
    DELETE_GRADE_REPORT(new DeleteGradeReportCommand()),
    EDIT_GRADE_REPORT(new EditGradeReportCommand()),
    SHOW_ALL_GRADE_REPORTS(new GetAllGradeReportsCommand()),
    SHOW_GRADE_REPORTS_BY_FACULTY(new GetGradeReportsByFacultyCommand());

    private Command command;

    CommandFactory(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
