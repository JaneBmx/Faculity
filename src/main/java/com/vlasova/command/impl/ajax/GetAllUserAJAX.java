package com.vlasova.command.impl.ajax;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.user.User;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.vlasova.command.RequestParams.*;

public class GetAllUserAJAX implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetAllUserAJAX.class);
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> list = userService.getAll();
            request.setAttribute(USER_LIST, list);
            LOGGER.info("All users was loaded.");
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
            LOGGER.info("Users was not loaded.", e);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}