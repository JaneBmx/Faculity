package com.vlasova.controller.command.impl.user;

import com.vlasova.controller.command.Answer;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.controller.command.web.PageAddress;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vlasova.controller.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);
    private final UserService userService = UserService.getInstance();
    private final GradeReportService gradeReportService = GradeReportService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String login = request.getParameter(LOGIN);
        String password = request.getParameter(PASS);

        if (login != null && password != null) {
            try {
                User user = userService.logIn(login, password);
                if (user != null) {
                    request.getSession().setAttribute(USER, user);
                    request.getSession().setAttribute(ROLE, user.getRole() == Role.ADMIN ? ADMIN : USER);
                    GradeReport gradeReport = gradeReportService.getGradeReportByUserId(user.getId());
                    if (gradeReport != null) {
                        request.getSession().setAttribute(GRADE_REPORT, gradeReport);
                    }
                    LOGGER.info("Log in: "+user.getLogin());
                    return user.getRole() == Role.ADMIN
                            ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                            : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
                }
                request.setAttribute(MSG, MSG_WRONG_LOG_IN);
            } catch (ServiceException e) {
                request.setAttribute(MSG, MSG_SERV_ERR);
            }
        }
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}
