package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogOutCommand implements UserCommand{
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
//        request.getSession().removeAttribute(USER);
//        request.getSession().removeAttribute(GRADE_REPORT);
        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}
