package com.vlasova.command.impl.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.mapper.UserRequestMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.validation.UserDataValidator;
import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements UserCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new UserRequestMapper().map(request);
        if(UserDataValidator.isValidUser(user)){
            try{
                UserService.getInstance().registrate(user);
                request.getSession().setAttribute(USER, user);
                return PageAddress.USER_PAGE;

            }catch (ServiceException e){
                request.setAttribute(MSG, MSG_ERR_WRONG_PAS_OR_LOG);
            }
        }else{
            request.setAttribute(MSG, MSG_ERR_INV_DATA);
        }
        return PageAddress.SIGN_UP;
    }
}