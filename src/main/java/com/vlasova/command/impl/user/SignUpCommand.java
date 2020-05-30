package com.vlasova.command.impl.user;

import com.vlasova.command.Answer;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.mapper.UserRequestMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.service.UserService;
import com.vlasova.validation.UserDataValidator;

import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements UserCommand {
    private final UserDataValidator userDataValidator = new UserDataValidator();
    private final UserService userService = UserService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        User user = new UserRequestMapper().map(request);


        //if(userDataValidator.isValidUser(user)){
        if (true) {
            try {
                if(user.getRole()== Role.ADMIN){
                    userService.registrate(user);
                    return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
                }
                userService.registrateAndLogin(user);
                request.getSession().setAttribute(USER, user);
                return new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                request.setAttribute(MSG, MSG_ERR_WRONG_PAS_OR_LOG);
            }
        }
        request.setAttribute(MSG, MSG_ERR_INV_DATA);
        return new Answer(PageAddress.SIGN_UP, Answer.Type.FORWARD);
    }
}