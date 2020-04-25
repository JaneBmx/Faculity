package com.vlasova.command.impl.user;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.params.PageAddress;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignInCommand implements UserCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            User user = UserService.getInstance().logIn((String) request.getAttribute("login"),
                    (String) request.getAttribute("password"));

            if (user != null) {
                request.getSession().setAttribute("user", user);
                return user.getRole() == Role.ADMIN
                        ? PageAddress.ADMIN_PAGE
                        : PageAddress.USER_PAGE;
            }
            request.setAttribute("message", "Wrong login or password. Try again");
        } catch (ServiceException e) {
            request.setAttribute("message", "Wrong login or password. Try again");
        }
        return PageAddress.LOG_IN;
    }
}
