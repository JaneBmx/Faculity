package com.vlasova.command.impl.gradereport;

import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.GradeReport;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditGradeReportCommand implements GradeReportCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        GradeReport gradeReport = new GradeReport();


        return null;
    }
}
