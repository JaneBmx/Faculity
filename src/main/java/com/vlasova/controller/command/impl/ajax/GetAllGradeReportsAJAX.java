package com.vlasova.controller.command.impl.ajax;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.GradeReportService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.vlasova.controller.command.RequestParams.*;

public class GetAllGradeReportsAJAX implements Command {
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<GradeReport> list = gradeReportService.getAll();
            request.setAttribute(GR_LIST, list);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}