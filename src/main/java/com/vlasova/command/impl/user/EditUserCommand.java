package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.impl.user.UserConstants.*;

public class EditUserCommand implements UserCommand {
//TODO add validation, add trim

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if(request.getParameter(NEW_PASS )!=null
                && request.getParameter(PASS)!=null
                &&request.getParameter(PASS).equals(user.getPassword())){
            user.setPassword(request.getParameter(NEW_PASS));
        }

        if(request.getParameter(NAME)!=null){
            user.setName(request.getParameter(NAME));
        }
        if(request.getParameter(SURNAME)!=null){
            user.setSurname(request.getParameter(SURNAME));
        }
        try {
            UserService.getInstance().editUser(user);
        }catch (ServiceException e){
            request.setAttribute(MSG, MSG_SERV_ERR);
            return PageAddress.USER_PAGE;
        }
        request.getSession().setAttribute(USER, user);
        request.setAttribute(MSG, MSG_UPD_DATA_SCC);
        return PageAddress.USER_PAGE;
    }
}