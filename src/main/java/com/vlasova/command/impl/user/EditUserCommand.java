package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.mapper.request.UserRequestMapper;
import com.vlasova.params.PageAddress;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vlasova.validation.UserDataValidator.*;

public class EditUserCommand implements UserCommand {

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new UserRequestMapper().map(request);
        try {
            if (isValidUser(user)) {
                UserService.SERVICE.editUser(user);
                request.getSession().setAttribute("user", user);
                request.setAttribute("message", "Data has been update");
            }
        } catch (ServiceException e) {
            request.setAttribute("message", "Can't update user");
        }
        request.setAttribute("message", "Invalid data. Can't update user");
        return PageAddress.USER_PAGE;
    }
}
