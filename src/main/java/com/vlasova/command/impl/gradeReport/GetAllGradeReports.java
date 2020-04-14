package com.vlasova.command.impl.gradeReport;

import com.vlasova.command.web.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllGradeReports implements GradeReportCommand {
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
