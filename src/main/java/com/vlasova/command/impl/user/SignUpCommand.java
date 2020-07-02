package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.user.User;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.command.mapper.UserRequestMapper;
import com.vlasova.command.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.utill.validator.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class SignUpCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private final UserDataValidator userDataValidator = new UserDataValidator();
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new UserRequestMapper().map(request);
        if (userDataValidator.isValidUser(user)) {
            try {
                user = userService.registrateAndLogin(user);
                request.getSession().setAttribute(USER, user);
                request.getSession().setAttribute(ROLE, USER);
                LOGGER.info("Sign up: " + user.getLogin());
                return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                request.setAttribute(MSG_SIGNUP, MSG_WRONG_LOG_IN);
            }
        }
        request.setAttribute(MSG_SIGNUP, MSG_ERR_INV_DATA);
        return new Answer(PageAddress.SIGN_UP, Answer.Type.FORWARD);
    }
}