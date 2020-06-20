package com.vlasova.controller.command.impl.ajax;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import static com.vlasova.controller.command.RequestParams.*;

public class GetAllFacultiesAJAX implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetAllFacultiesAJAX.class);
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            List<Faculty> list = facultyService.getAll();
            request.setAttribute(FACULTY_LIST, list);
            LOGGER.info("All faculties was loaded.");
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
            LOGGER.warn("Faculties was not loaded.", e);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}