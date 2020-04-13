package com.vlasova.command.factory;

import com.vlasova.command.impl.faculty.AddFaculty;
import com.vlasova.command.impl.faculty.DeleteFaculty;
import com.vlasova.command.impl.faculty.EditFaculty;
import com.vlasova.command.impl.faculty.FacultyCommand;
import com.vlasova.command.impl.faculty.GetAllFaculties;
import com.vlasova.command.impl.faculty.GetAllFreePaidFaculties;
import com.vlasova.command.impl.faculty.GetFacultiesBySubject;
import com.vlasova.command.impl.faculty.GetFacultyById;

public enum FacultyCommandFactory {
    SHOW_ALL_FACULTIES(new GetAllFaculties()),
    ADD_FACULTY(new AddFaculty()),
    DELETE_FACULTY(new DeleteFaculty()),
    EDIT_FACULTY(new EditFaculty()),
    SHOW_ALL_FREE_PAID_FACULTY(new GetAllFreePaidFaculties()),
    SHOW_FACULTY_BY_ID(new GetFacultyById()),
    SHOW_FACULTY_BY_SUBJECT(new GetFacultiesBySubject());

    private FacultyCommand facultyCommand;

    FacultyCommandFactory(FacultyCommand facultyCommand) {
        this.facultyCommand = facultyCommand;
    }

    public FacultyCommand getFacultyCommand() {
        return facultyCommand;
    }
}
