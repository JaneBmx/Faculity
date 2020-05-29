package com.vlasova.command.mapper;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;

public class UserRequestMapper {
    //TODO check NPE
    public User map(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(USER_NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASS));
        user.setRole(Role.valueOf(request.getParameter(ROLE).toUpperCase()));
        return user;
    }
}
