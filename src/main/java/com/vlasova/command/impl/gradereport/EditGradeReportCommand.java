package com.vlasova.command.impl.gradereport;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.mapper.GradeReportMapper;
import com.vlasova.command.PageAddress;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.command.exception.InvalidRequestDataException;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.GradeReportService;
import com.vlasova.controller.utill.validator.GradeReportValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class EditGradeReportCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditGradeReportCommand.class);
    private final GradeReportMapper gradeReportMapper = new GradeReportMapper();
    private final GradeReportService gradeReportService = GradeReportService.getInstance();
    private final GradeReportValidator gradeReportValidator = new GradeReportValidator();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        int userID = Integer.parseInt(request.getParameter(USER_ID));
        GradeReport reqGradeReport;

        try {
            reqGradeReport = gradeReportMapper.map(request);
            if (!gradeReportValidator.isValidGradeReport(reqGradeReport)) {
                throw new InvalidRequestDataException();
            }
            reqGradeReport.setId(userID);
        } catch (InvalidRequestDataException e) {
            request.setAttribute(MSG, MSG_ERR_INV_DATA);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }

        GradeReport dbGradeReport;
        try {
            dbGradeReport = gradeReportService.getGradeReportByUserId(userID);
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(MSG, MSG_SERV_ERR);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }

        try {
            if (dbGradeReport == null) {
                gradeReportService.add(reqGradeReport);
            } else {
                gradeReportService.update(reqGradeReport);
            }
            reqGradeReport = gradeReportService.getGradeReportByUserId(userID);
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(MSG, MSG_SERV_ERR);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }
        request.getSession().setAttribute(GRADE_REPORT, reqGradeReport);
        return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
    }
}