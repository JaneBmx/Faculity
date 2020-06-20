package com.vlasova.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    Answer execute(HttpServletRequest request, HttpServletResponse response);
}
