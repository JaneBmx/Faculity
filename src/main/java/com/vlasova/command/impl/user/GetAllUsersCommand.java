package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;

import static com.vlasova.command.impl.user.UserConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class GetAllUsersCommand implements UserCommand {
    //TODO add pagination

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<User> users = UserService.getInstance().getAllUsers();
            request.getSession().setAttribute("users", users);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return PageAddress.ADMIN_PAGE;
    }
}
