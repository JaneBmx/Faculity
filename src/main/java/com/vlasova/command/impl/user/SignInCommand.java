package com.vlasova.command.impl.user;

import com.vlasova.controller.Controller;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vlasova.command.impl.user.UserConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    private static final Logger LOGGER = LogManager.getLogger(SignInCommand.class);

    //TODO add PRD for safe F5

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = UserService.getInstance().logIn(request.getParameter(LOGIN),
                    request.getParameter(PASS));

            if (user != null) {
                request.getSession().setAttribute(USER, user);

                if(getGradeReport(user)!=null){
                    request.getSession().setAttribute(GRADE_REPORT, getGradeReport(user));
                }

                return user.getRole() == Role.ADMIN
                        ? PageAddress.ADMIN_PAGE
                        : PageAddress.USER_PAGE;
            }
            request.setAttribute(MSG, MSG_ERR_WRONG_PAS_OR_LOG);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return PageAddress.LOG_IN;
    }
    private GradeReport getGradeReport(User user){
        try {
            return GradeReportService.getInstance().getGradeReportByUser(user);
        } catch (ServiceException e) {
            LOGGER.warn("GradeReport for user (with id: "+ user.getId()+ ") hasn't been loaded.", e);
        }
        return null;
    }
}
