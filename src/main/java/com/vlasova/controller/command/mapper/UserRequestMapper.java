package com.vlasova.controller.command.mapper;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import static com.vlasova.controller.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;

public class UserRequestMapper {
    public User map(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(USER_NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASS));
        if (request.getParameter(USER_ROLE) != null) {
            user.setRole(Role.valueOf(request.getParameter(USER_ROLE).toUpperCase()));
        }
        return user;
    }
}