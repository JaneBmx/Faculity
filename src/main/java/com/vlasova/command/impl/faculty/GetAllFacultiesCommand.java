package com.vlasova.command.impl.faculty;

import com.vlasova.params.PageAddress;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllFacultiesCommand implements FacultyCommand {
    @Override
    public PageAddress execute(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
