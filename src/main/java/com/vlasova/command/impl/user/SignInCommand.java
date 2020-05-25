package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.UserService;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = UserService.getInstance().logIn(request.getParameter(LOGIN),
                    request.getParameter(PASS));
            if (user != null) {
                request.getSession().setAttribute(USER, user);
                GradeReport gradeReport = GradeReportService.getInstance().getGradeReportByUser(user);
                if (gradeReport != null) {
                    request.getSession().setAttribute(GRADE_REPORT, gradeReport);
                }
                return user.getRole() == Role.ADMIN
                        ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                        : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
            }
            request.setAttribute(MSG, MSG_ERR_WRONG_PAS_OR_LOG);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}
