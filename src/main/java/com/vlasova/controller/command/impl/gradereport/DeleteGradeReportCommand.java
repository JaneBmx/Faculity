package com.vlasova.controller.command.impl.gradereport;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.web.PageAddress;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.GradeReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteGradeReportCommand implements GradeReportCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteGradeReportCommand.class);
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String gradeId = request.getParameter("grade_report_id");
        if (gradeId != null) {
            int gradeID = Integer.parseInt(gradeId);
            try {
                gradeReportService.remove(gradeID);
                LOGGER.info("Delete gradeReport with id:" + gradeID);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            }
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}