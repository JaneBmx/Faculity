package com.vlasova.command.impl.user;

import com.vlasova.command.PageEnum;
import com.vlasova.command.Parameter;
import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUserCommand implements UserCommand {

    private final UserService userService;

    public RegisterUserCommand() {
        this.userService = UserService.getInstance();
    }

    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(Parameter.NAME.getParameter());
        String surname = request.getParameter(Parameter.SURNAME.getParameter());
        String email = request.getParameter(Parameter.EMAIL.getParameter());
        String login = request.getParameter(Parameter.LOGIN.getParameter());
        String password = request.getParameter(Parameter.PASSWORD.getParameter());
        User user = new User();
        user.setRole(Role.ENROLLEE);
        user.setName(name);
        user.setSurname(surname);
        user.setEmail(email);
        user.setLogin(login);
        user.setPassword(password);
        user.setPrivilege(Privilege.NONE);
        try {
            userService.registration(user);
            return PageEnum.USER_PAGE;
        } catch (ServiceException e) {
            return PageEnum.REGISTRATION;
        }
    }
}
