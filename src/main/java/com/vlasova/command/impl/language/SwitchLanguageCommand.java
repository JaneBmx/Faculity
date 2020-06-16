package com.vlasova.command.impl.language;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SwitchLanguageCommand implements Command {
    private static final String LANG         = "lang";
    private static final String DEFAULT_LANG = "EN";
    private static final int    COOKIE_AGE   = 60*60*24; // 1 day

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Object language = request.getSession().getAttribute(LANG);

        String lang = language == null ? DEFAULT_LANG : language.toString();

        Cookie cookie = new Cookie(LANG, lang);
        cookie.setMaxAge(COOKIE_AGE);
        cookie.setPath(request.getContextPath());
        response.addCookie(cookie);

        request.getSession().setAttribute(LANG, lang);

        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}
