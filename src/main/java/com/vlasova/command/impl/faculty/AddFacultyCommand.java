package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.mapper.FacultyMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddFacultyCommand implements FacultyCommand {
    private final FacultyMapper mapper = new FacultyMapper();
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = mapper.map(request);

        try {
            facultyService.addFaculty(faculty);
        } catch (ServiceException e) {

        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}
