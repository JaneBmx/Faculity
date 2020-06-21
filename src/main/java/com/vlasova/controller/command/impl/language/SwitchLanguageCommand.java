package com.vlasova.controller.command.impl.language;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.controller.command.PageAddress;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SwitchLanguageCommand implements Command {
    private static final int COOKIE_AGE = 60 * 60 * 24;
    private static final String LANG = "lang";
    private static final String DEFAULT_LANG = "EN";

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        /** Get current lang
         */
        Object lngO = request.getSession().getAttribute(LANG);
        String lng = lngO == null ? DEFAULT_LANG : lngO.toString();
        /** Switch to another
         */
        String lang;

        switch (lng) {
            case "EN":
                lang = "RU";
                break;
            case "RU":
            default:
                lang = "EN";
                break;
        }
        Cookie cookie = new Cookie(LANG, lang);
        cookie.setMaxAge(COOKIE_AGE);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        request.getSession().setAttribute(LANG, lang);
        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}