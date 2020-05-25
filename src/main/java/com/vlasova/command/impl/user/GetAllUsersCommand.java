package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class GetAllUsersCommand implements UserCommand {
    //TODO add json& js for load set of users on page
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Set<User> users = UserService.getInstance().getAllUsers();
            request.getSession().setAttribute(USERS, users);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}
