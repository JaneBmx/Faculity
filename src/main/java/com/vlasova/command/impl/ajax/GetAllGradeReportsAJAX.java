package com.vlasova.command.impl.ajax;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.GradeReportService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.vlasova.command.RequestParams.*;

public class GetAllGradeReportsAJAX implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetAllGradeReportsAJAX.class);
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<GradeReport> list = gradeReportService.getAll();
            request.setAttribute(GR_LIST, list);
            LOGGER.info("All grade reports was loaded.");
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
            LOGGER.warn("Grade reports wasn't loaded.", e);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}