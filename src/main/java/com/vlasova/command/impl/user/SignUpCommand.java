package com.vlasova.command.impl.user;

import com.vlasova.params.PageAddress;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpCommand implements UserCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}