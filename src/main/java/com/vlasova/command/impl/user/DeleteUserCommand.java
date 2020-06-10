package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements UserCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteUserCommand.class);
    private static final String adminId = "1";
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter("user_id");
        if (userId != null && !userId.equalsIgnoreCase(adminId)) {
            int userID = Integer.parseInt(userId);
            try {
                userService.delete(userID);
                LOGGER.info("Delete user with id: "+userID);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            }
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}