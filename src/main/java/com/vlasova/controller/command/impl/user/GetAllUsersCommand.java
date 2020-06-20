package com.vlasova.controller.command.impl.user;

import com.vlasova.controller.command.Answer;
import com.vlasova.entity.user.User;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.controller.command.web.PageAddress;
import com.vlasova.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.vlasova.controller.command.RequestParams.*;

public class GetAllUsersCommand implements UserCommand {
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<User> users = userService.getAll();
            request.getSession().setAttribute(USERS, users);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}