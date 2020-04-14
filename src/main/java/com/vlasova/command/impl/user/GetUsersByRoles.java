package com.vlasova.command.impl.user;

import com.vlasova.command.web.PageEnum;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUsersByRoles implements UserCommand {
    @Override
    public PageEnum execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
