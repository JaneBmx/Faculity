package com.vlasova.command.impl.gradereport;

import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllGradeReportsCommand implements GradeReportCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
