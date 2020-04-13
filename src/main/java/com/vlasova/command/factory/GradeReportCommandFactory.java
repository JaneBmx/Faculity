package com.vlasova.command.factory;

import com.vlasova.command.impl.gradeReport.AddGradeReport;
import com.vlasova.command.impl.gradeReport.ChangeGradeReportStatus;
import com.vlasova.command.impl.gradeReport.DeleteGradeReport;
import com.vlasova.command.impl.gradeReport.EditGradeReport;
import com.vlasova.command.impl.gradeReport.GetAllGradeReports;
import com.vlasova.command.impl.gradeReport.GetGradeReportsByFaculty;
import com.vlasova.command.impl.gradeReport.GradeReportCommand;

public enum GradeReportCommandFactory {

    ADD_GRADE_REPORT(new AddGradeReport()),
    CHANGE_GRADE_REPORT_STATUS(new ChangeGradeReportStatus()),
    DELETE_GRADE_REPORT(new DeleteGradeReport()),
    EDIT_GRADE_REPORT(new EditGradeReport()),
    SHOW_ALL_GRADE_REPORTS(new GetAllGradeReports()),
    SHOW_GRADE_REPORTS_BY_FACULTY(new GetGradeReportsByFaculty());

    private GradeReportCommand gradeReportCommand;

    GradeReportCommandFactory(GradeReportCommand gradeReportCommand) {
        this.gradeReportCommand = gradeReportCommand;
    }

    public GradeReportCommand getGradeReportCommand() {
        return gradeReportCommand;
    }
}
