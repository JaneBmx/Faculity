package com.vlasova.command.impl.user;

import com.vlasova.command.web.PageEnum;
import com.vlasova.command.web.Parameter;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.factory.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogIn implements UserCommand {
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
    try{
            ServiceFactory.getInstance().getUserService()
                    .logIn(request.getParameter(Parameter.LOGIN.getParameter())
                            ,request.getParameter(Parameter.PASSWORD.getParameter()));
            return PageEnum.USER_PAGE;
        }catch (ServiceException e){
            return PageEnum.LOGIN;
        }
    }
}
