package com.vlasova.command.impl.user;
import com.vlasova.command.web.PageEnum;
import com.vlasova.command.web.Parameter;
import com.vlasova.entity.user.Privilege;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.factory.ServiceFactory;
import com.vlasova.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterUser implements UserCommand {
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter(Parameter.NAME.getParameter());
        String surname = request.getParameter(Parameter.SURNAME.getParameter());
        String email = request.getParameter(Parameter.EMAIL.getParameter());
        String login = request.getParameter(Parameter.LOGIN.getParameter());
        String password = request.getParameter(Parameter.PASSWORD.getParameter());
        try {
            ServiceFactory serviceFactory = ServiceFactory.getInstance();
            UserService userService = serviceFactory.getUserService();
            userService.registration(name,surname,email,login,password, Privilege.NONE);
            return PageEnum.USER_PAGE;
        }catch (ServiceException e){
            return PageEnum.REGISTRATION;
        }
    }
}
