package com.vlasova.command;

import com.vlasova.params.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {
    PageAddress execute(HttpServletRequest request, HttpServletResponse response);
}
