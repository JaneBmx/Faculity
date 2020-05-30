package com.vlasova.command.impl.ajax;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;
import com.vlasova.service.GradeReportService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.vlasova.command.RequestParams.*;

public class GetAllGradeReportsAJAX implements Command {
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<GradeReport> list = gradeReportService.getAllGradeReports();
            request.setAttribute(GR_LIST, list);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}
