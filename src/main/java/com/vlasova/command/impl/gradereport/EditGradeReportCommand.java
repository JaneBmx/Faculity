package com.vlasova.command.impl.gradereport;

import com.vlasova.command.mapper.GradeReportMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.GradeReportService;
import com.vlasova.validation.GradeReportValidator;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditGradeReportCommand implements GradeReportCommand {

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        GradeReport gradeReport = new GradeReportMapper().map(request);
        if (GradeReportValidator.isValidGradeReport(gradeReport)) {
            gradeReport.setId(((User) request.getSession().getAttribute(USER)).getId());
            try {
                GradeReportService.getInstance().addGradeReport(gradeReport);
                gradeReport = GradeReportService.getInstance()
                        .getGradeReportByUser((User) request.getSession().getAttribute(USER));
                request.getSession().setAttribute(GRADE_REPORT, gradeReport);
                request.setAttribute(MSG, MSG_UPD_DATA_SCC);
                return PageAddress.USER_PAGE;
            } catch (ServiceException e) {
                request.setAttribute(MSG, MSG_SERV_ERR);
            }
        }
        request.setAttribute(MSG, MSG_ERR_INV_DATA);
        return PageAddress.USER_PAGE;
    }
}
