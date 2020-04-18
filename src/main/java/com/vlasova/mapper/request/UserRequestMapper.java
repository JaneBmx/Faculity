package com.vlasova.mapper.request;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import javax.servlet.http.HttpServletRequest;

public class UserRequestMapper {
    private static final String NAME = "user_name";
    private static final String SURNAME = "user_surname";
    private static final String EMAIL = "user_email";
    private static final String LOGIN = "user_login";
    private static final String PASSWORD = "user_password";

    public User map(HttpServletRequest request) {
        User user = new User();
        user.setName(request.getParameter(NAME));
        user.setSurname(request.getParameter(SURNAME));
        user.setEmail(request.getParameter(EMAIL));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        user.setRole(Role.USER);
        return user;
    }
}
