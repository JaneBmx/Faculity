package com.vlasova.command.impl.faculty;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.mapper.FacultyMapper;
import com.vlasova.command.PageAddress;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.command.exception.InvalidRequestDataException;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.FacultyService;
import com.vlasova.utill.validator.FacultyValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.command.RequestParams.*;

public class AddFacultyCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(AddFacultyCommand.class);
    private final FacultyValidator validator = new FacultyValidator();
    private final FacultyMapper mapper = new FacultyMapper();
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Faculty faculty;
        try {
            faculty = mapper.map(request);
            if (!validator.isValidFaculty(faculty) || facultyService.isExistByName(faculty.getName()))
                throw new InvalidRequestDataException("Invalid faculty data.");

        } catch (InvalidRequestDataException | ServiceException e) {
            request.setAttribute(MSG_ADD_FACULTY, MSG_ERR_INV_DATA);
            return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
        }

        try {
            facultyService.add(faculty);
            LOGGER.info("New faculty has been added.");
            return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
        } catch (ServiceException e) {
            LOGGER.info("New faculty hasn't been added.", e);
            request.setAttribute(MSG_ADD_FACULTY, MSG_SERV_ERR);
            return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
        }
    }
}