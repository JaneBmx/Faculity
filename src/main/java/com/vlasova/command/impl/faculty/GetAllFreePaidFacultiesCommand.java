package com.vlasova.command.impl.faculty;

import com.vlasova.command.web.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllFreePaidFacultiesCommand implements FacultyCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
