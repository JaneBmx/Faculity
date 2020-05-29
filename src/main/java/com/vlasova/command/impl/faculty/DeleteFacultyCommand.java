package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;
import static com.vlasova.command.RequestConstants.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFacultyCommand implements FacultyCommand {
    private final FacultyService facultyService = FacultyService.getInstance();
    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String reqParam = request.getParameter(FACULTY_ID);
        if (reqParam != null) {
            int facultyId = Integer.parseInt(reqParam);
            try {
                facultyService.deleteFaculty(facultyId);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            } catch (ServiceException e) {
                return  new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
            }
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}
