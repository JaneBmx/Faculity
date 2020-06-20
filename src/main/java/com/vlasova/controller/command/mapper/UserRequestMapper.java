package com.vlasova.controller.command.mapper;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import javax.servlet.http.HttpServletRequest;

public class UserRequestMapper {
    private static final String USER_NAME  = "user_name";
    private static final String SURNAME    = "user_surname";
    private static final String EMAIL      = "user_email";
    private static final String LOGIN      = "user_login";
    private static final String PASS       = "user_password";
    private static final String ROLE       = "user_role";

    public User map(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(USER_NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASS));
        if (request.getParameter(ROLE) != null) {
            user.setRole(Role.valueOf(request.getParameter(ROLE).toUpperCase()));
        }
        return user;
    }
}