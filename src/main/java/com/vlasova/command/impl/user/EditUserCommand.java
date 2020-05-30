package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.validation.UserDataValidator;

import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand implements UserCommand {
    private final UserDataValidator validator = new UserDataValidator();
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(USER);
        String newPassword = request.getParameter(NEW_PASS);
        String oldPassword = request.getParameter(PASS);
        String name = request.getParameter(USER_NAME);
        String surname = request.getParameter(SURNAME);

        if (validator.isValidPassword(newPassword)
                && validator.isValidPassword(oldPassword)
                && oldPassword.equals(user.getPassword())) {
            newPassword = newPassword.trim();
            oldPassword = oldPassword.trim();
            if (oldPassword.equals(user.getPassword())) {
                user.setPassword(newPassword);
            }
        }
        if (validator.isValidName(name)) {
            name = name.trim();
            user.setName(name);
        }
        if (validator.isValidName(surname)) {
            surname = surname.trim();
            user.setSurname(surname);
        }
        try {
            userService.editUser(user);
            user = userService.getUserById(user.getId());
            request.getSession().setAttribute(USER, user);
            request.setAttribute(MSG, MSG_UPD_DATA_SCC);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }
    }
}