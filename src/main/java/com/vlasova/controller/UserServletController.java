package com.vlasova.controller;

import com.vlasova.command.PageEnum;
import com.vlasova.command.factory.UserCommandFactory;
import com.vlasova.command.impl.user.UserCommand;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/users")
public class UserServletController extends HttpServlet {

    private final static String COMMAND_PARAMETER = "command";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String parameter = req.getParameter(COMMAND_PARAMETER);
        UserCommand userCommand = UserCommandFactory.valueOf(parameter.toUpperCase()).getUserCommand();
        PageEnum page = userCommand.execute(req, resp);
        //
    }
}
