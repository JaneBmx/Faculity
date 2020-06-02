package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class LogOutCommand implements UserCommand{
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().invalidate();
        request.getSession().setAttribute(ROLE, GUEST);
        return new Answer(PageAddress.HOME_PAGE, Answer.Type.REDIRECT);
    }
}
