package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.mapper.UserRequestMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;
import com.vlasova.validation.UserDataValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddUserCommand.class);
    private final UserService userService = UserService.getInstance();
    private final UserDataValidator validator = new UserDataValidator();
    private final UserRequestMapper mapper = new UserRequestMapper();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = mapper.map(request);
        if(validator.isValidUser(user)){
            try {
                userService.registrate(user);
                LOGGER.info("New user: "+user.getLogin()+ "with role: "+user.getRole());
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                request.setAttribute(MSG, MSG_SERV_ERR);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            }
        }
        request.setAttribute(MSG, MSG_ERR_INV_DATA);
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}
