package com.vlasova.controller.command.impl.user;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.controller.command.PageAddress;
import com.vlasova.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.controller.command.RequestParams.*;

public class LogOutCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LogOutCommand.class);
    private static final int COOKIE_AGE = 60 * 60 * 24;
    private static final String LANG = "lang";
    private static final String DEFAULT_LANG = "EN";

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String name = ((User) request.getSession().getAttribute(USER)).getLogin();
        String language = request.getSession().getAttribute(LANG).toString();
        request.getSession().invalidate();
        LOGGER.info("Logout: " + name);
        request.getSession().setAttribute(ROLE, GUEST);
        Cookie cookie = new Cookie(LANG, language);
        cookie.setMaxAge(COOKIE_AGE);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);
        request.getSession().setAttribute(LANG, language);
        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}