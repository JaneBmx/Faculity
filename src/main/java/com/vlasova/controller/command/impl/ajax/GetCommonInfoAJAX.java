package com.vlasova.controller.command.impl.ajax;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.service.FacultyService;
import com.vlasova.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

import static com.vlasova.controller.command.RequestParams.*;

public class GetCommonInfoAJAX implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetCommonInfoAJAX.class);
    private final FacultyService facultyService = FacultyService.getInstance();

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, Integer> info = facultyService.getCommonInfo();
            request.setAttribute("info", info);
        } catch (ServiceException e) {
            request.setAttribute(MSG, MSG_SERV_ERR);
            LOGGER.warn("Common info wasn't loaded.", e);
        }
        return new Answer(null, Answer.Type.JSON);
    }
}