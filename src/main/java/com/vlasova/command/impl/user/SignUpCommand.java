package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.mapper.request.UserRequestMapper;
import com.vlasova.params.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.validation.UserDataValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements UserCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new UserRequestMapper().map(request);
        if(UserDataValidator.isValidUser(user)){
            try{
                UserService.getInstance().registration(user);
                request.getSession().setAttribute("user", user);
                return PageAddress.USER_PAGE;
            }catch (ServiceException e){
                request.setAttribute("message", "User with those login and email is already exist");
            }
        }else{
            request.setAttribute("message", "Invalid data");
        }
        return PageAddress.SIGN_UP;
    }
}