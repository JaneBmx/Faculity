package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.params.PageAddress;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class GetAllUsersCommand implements UserCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<User> users = UserService.getInstance().getAllUsers();
            request.getSession().setAttribute("users", users);
        } catch (ServiceException e) {
            request.setAttribute("message", "Some problems with server. Try again later");
        }
        return PageAddress.ADMIN_PAGE;
    }
}
