package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.faculty.*;
import com.vlasova.command.impl.gradeReport.*;
import com.vlasova.command.impl.user.*;

public enum CommandFactory {
    REGISTRATION(new RegisterUser()),
    LOGIN(new LogIn()),

    EDIT_USER(new EditUser()),
    DELETE_USER(new DeleteUser()),
    GET_USERS_BY_ROLE(new GetUsersByRoles()),
    GET_USER_BY_ID(new GetUserById()),

    SHOW_ALL_FACULTIES(new GetAllFaculties()),
    ADD_FACULTY(new AddFaculty()),
    DELETE_FACULTY(new DeleteFaculty()),
    EDIT_FACULTY(new EditFaculty()),
    SHOW_ALL_FREE_PAID_FACULTY(new GetAllFreePaidFaculties()),
    SHOW_FACULTY_BY_ID(new GetFacultyById()),
    SHOW_FACULTY_BY_SUBJECT(new GetFacultiesBySubject()),

    ADD_GRADE_REPORT(new AddGradeReport()),
    CHANGE_GRADE_REPORT_STATUS(new ChangeGradeReportStatus()),
    DELETE_GRADE_REPORT(new DeleteGradeReport()),
    EDIT_GRADE_REPORT(new EditGradeReport()),
    SHOW_ALL_GRADE_REPORTS(new GetAllGradeReports()),
    SHOW_GRADE_REPORTS_BY_FACULTY(new GetGradeReportsByFaculty());

    private Command command;

    private CommandFactory(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
