package com.vlasova.controller.command.impl.user;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.web.PageAddress;
import com.vlasova.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.vlasova.controller.command.RequestParams.*;

public class LogOutCommand implements UserCommand {
    private static final Logger LOGGER = LogManager.getLogger(LogOutCommand.class);

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String name = ((User) request.getSession().getAttribute("user")).getLogin();
        request.getSession().invalidate();
        request.getSession().setAttribute(ROLE, GUEST);
        LOGGER.info("Logout: " + name);
        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}
