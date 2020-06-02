package com.vlasova.command.impl.page;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogInPageCommand implements Command {
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String role = (String) request.getAttribute("role");

        if (role != null && role.equalsIgnoreCase("user")) {
            return new Answer(PageAddress.USER_PAGE, Answer.Type.FORWARD);
        }
        if (role != null && role.equalsIgnoreCase("admin")) {
            return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
        }
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}
