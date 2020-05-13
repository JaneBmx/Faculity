package com.vlasova.command.impl.user;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;
import static com.vlasova.command.impl.user.UserConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    //TODO add PRD for safe F5

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = UserService.getInstance().logIn(request.getParameter(LOGIN),
                    request.getParameter(PASS));

            if (user != null) {
                request.getSession().setAttribute(USER, user);
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
}
