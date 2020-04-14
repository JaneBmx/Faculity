package com.vlasova.command.impl.gradeReport;

import com.vlasova.command.web.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeGradeReportStatus implements GradeReportCommand{
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
    /*
     * Use
     * com.vlasova.service.impl.GradeReportServiceImpl.setGradeReportAcceptAndFree
     * for set isFreePaid, isAccepted
     */
}
