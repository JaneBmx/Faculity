package com.vlasova.command;

import com.vlasova.command.web.WebPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    WebPath execute(HttpServletRequest request, HttpServletResponse response);
}
