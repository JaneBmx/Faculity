package com.vlasova.command.impl.ajax;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.vlasova.command.RequestParams.*;

public class GetAllUserAJAX implements Command {
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> list = userService.getAllUsers();
            request.setAttribute(USER_LIST, list);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}