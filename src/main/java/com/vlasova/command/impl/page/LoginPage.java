package com.vlasova.command.impl.page;

import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestConstants.*;

public class LoginPage implements Command {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute(USER);
        if(user == null){
            return PageAddress.LOG_IN;
        }
        return user.getRole() == Role.ADMIN
                ? PageAddress.ADMIN_PAGE
                : PageAddress.USER_PAGE;
    }
}
