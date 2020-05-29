package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.mapper.FacultyMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;

import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddFacultyCommand implements FacultyCommand {
    private final FacultyMapper mapper = new FacultyMapper();
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = mapper.map(request);

        try {
            facultyService.addFaculty(faculty);
            List<Faculty> facultyList = facultyService.getAllFaculties();
            request.getServletContext().setAttribute(FACULTIES, facultyList);
        } catch (ServiceException e) {

        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}
