package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFacultyCommand implements FacultyCommand {
    private static final Logger LOGGER = LogManager.getLogger(DeleteFacultyCommand.class);
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String reqParam = request.getParameter(FACULTY_ID);
        if (reqParam != null) {
            int facultyId = Integer.parseInt(reqParam);
            try {
                facultyService.remove(facultyId);
                LOGGER.info("Delete faculty with id: "+facultyId);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            } catch (ServiceException e) {
                LOGGER.warn(e);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
            }
        }
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}