package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.vlasova.command.RequestConstants.*;

public class GetAllUserAJAX implements UserCommand {
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> list = userService.getAllUsers();
            //request.getSession().setAttribute(USERS, users);
            request.setAttribute("user_list", list);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        //return new Answer(PageAddress.AJAX, Answer.Type.JSON);
        return new Answer(null, Answer.Type.JSON);
    }
}
