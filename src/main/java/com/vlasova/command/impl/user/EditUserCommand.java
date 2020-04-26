package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.params.PageAddress;
import com.vlasova.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditUserCommand implements UserCommand {
//TODO add validation, add trim

    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute("user");

        if(request.getParameter("new_password" )!=null
                && request.getParameter("old_password")!=null
                &&request.getParameter("old_password").equals(user.getPassword())){
            user.setPassword(request.getParameter("new_password"));
        }

        if(request.getParameter("name")!=null){
            user.setName(request.getParameter("name"));
        }
        if(request.getParameter("surname")!=null){
            user.setSurname(request.getParameter("surname"));
        }
        try {
            UserService.getInstance().editUser(user);
        }catch (ServiceException e){
            request.setAttribute("message", "Some problems with server. Tra again later.");
            return PageAddress.USER_PAGE;
        }
        request.getSession().setAttribute("user", user);
        request.setAttribute("message", "Data has been updated");
        return PageAddress.USER_PAGE;
    }
}




//        User user = new UserRequestMapper().map(request);
//
//        try {
//            if (isValidUser(user)) {
//                UserService.getInstance().editUser(user);
//                request.getSession().setAttribute("user", user);
//                request.setAttribute("message", "Data has been update");
//            }
//        } catch (ServiceException e) {
//            request.setAttribute("message", "Can't update user");
//        }
//        request.setAttribute("message", "Invalid data. Can't update user");
//        return PageAddress.USER_PAGE;
