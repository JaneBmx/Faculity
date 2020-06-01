package com.vlasova.command.impl.gradereport;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.accept.Accepter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnEnrollCommand implements Command {
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Accepter accepter = new Accepter();
        try {
            accepter.unEnroll();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}
