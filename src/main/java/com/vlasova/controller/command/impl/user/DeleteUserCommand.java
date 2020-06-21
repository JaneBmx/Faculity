package com.vlasova.controller.command.impl.user;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.controller.command.PageAddress;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.controller.command.RequestParams.*;

public class DeleteUserCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private static final String ADMIN_ID = "1";
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(USER_ID);
        if (userId != null && !userId.equalsIgnoreCase(ADMIN_ID)) {
            int userID = Integer.parseInt(userId);
            try {
                userService.remove(userID);
                LOGGER.info("Delete user with id: "+userID);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            }
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}