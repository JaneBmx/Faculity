package com.vlasova.command.impl.page;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class ProfilePageCommand implements Command {
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute(USER);
        if(user == null){
            return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
        }
        return user.getRole() == Role.ADMIN
                ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
    }
}
