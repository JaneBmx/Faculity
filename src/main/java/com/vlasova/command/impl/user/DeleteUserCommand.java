package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand implements UserCommand {
    private  final UserService userService= UserService.getInstance();
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        int userID =  Integer.parseInt(request.getParameter("user_id"));
        try{
            userService.delete(userID);
        }catch (ServiceException e){
            //TODO add later
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}
