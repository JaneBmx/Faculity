package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;

import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAllUsersCommand implements UserCommand {
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = userService.getAllUsers();
            request.getSession().setAttribute(USERS, users);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}
