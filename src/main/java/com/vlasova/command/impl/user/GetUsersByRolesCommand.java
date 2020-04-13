package com.vlasova.command.impl.user;

import com.vlasova.command.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUsersByRolesCommand implements UserCommand {
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
