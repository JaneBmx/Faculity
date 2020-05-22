package com.vlasova.command.mapper;

import com.vlasova.entity.user.GradeReport;

import javax.servlet.http.HttpServletRequest;
import static com.vlasova.command.RequestConstants.*;

public class GradeReportMapper {

    public GradeReport map (HttpServletRequest request){
        GradeReport gradeReport = new GradeReport();
        gradeReport.setFaculty(request.getAttribute(FACULTY_ID));
    }
}
