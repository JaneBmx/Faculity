package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.mapper.FacultyMapper;
import com.vlasova.command.web.PageAddress;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;
import com.vlasova.util.validation.FacultyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import static com.vlasova.command.RequestParams.*;

public class AddFacultyCommand implements FacultyCommand {
    private static final Logger LOGGER = LogManager.getLogger(AddFacultyCommand.class);
    private final FacultyValidator validator = new FacultyValidator();
    private final FacultyMapper mapper = new FacultyMapper();
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty = mapper.map(request);
        if (validator.isValidFaculty(faculty)) {
            try {
                facultyService.addFaculty(faculty);
                List<Faculty> facultyList = facultyService.getAllFaculties();
                request.getServletContext().setAttribute(FACULTIES, facultyList);
                LOGGER.info("New faculty has been added.");
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
            } catch (ServiceException e) {
                LOGGER.info("New faculty hasn't been added.", e);
                request.setAttribute(MSG_ADD_FACULTY, MSG_SERV_ERR);
                return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
            }
        }
        request.setAttribute(MSG_ADD_FACULTY, MSG_ERR_INV_DATA);
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
    }
}