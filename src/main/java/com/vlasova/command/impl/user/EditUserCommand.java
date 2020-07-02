package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.user.User;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.command.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.controller.utill.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class EditUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUserCommand.class);
    private final UserDataValidator validator = new UserDataValidator();
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(USER);
        String newPassword = request.getParameter(NEW_PASS);
        String oldPassword = request.getParameter(PASS);
        String name = request.getParameter(USER_NAME);
        String surname = request.getParameter(SURNAME);

        String message = "";

        if (!validator.isValidName(name)) {
            message += "Invalid name.\n";
        }
        user.setName(name);

        if (!validator.isValidName(surname)) {
            message += "Invalid surname. \n";
        }
        user.setSurname(surname);

        if (!(validator.isValidPassword(newPassword)
                && validator.isValidPassword(oldPassword)
                && oldPassword.equals(user.getPassword()))) {
            message += "Invalid password. \n";
        }

        if (message.isEmpty()) {
            try {
                userService.update(user);
                user = userService.getUserById(user.getId());
                request.getSession().setAttribute(USER, user);
                LOGGER.info("User with id " + user.getId() + " edited user_info");
                return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                request.setAttribute(MSG_EDIT_INFO, MSG_SERV_ERR);
                return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
            }
        }
        request.setAttribute(MSG_EDIT_INFO, message);
        LOGGER.info("User with id " + user.getId() + " did'n edit data");
        return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
    }
}