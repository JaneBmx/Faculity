package com.vlasova.command.impl.gradereport;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.command.web.PageAddress;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.accept.Accepter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static com.vlasova.command.RequestParams.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EnrollGraderReportsCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(EnrollGraderReportsCommand.class);

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        Accepter accepter = new Accepter();
        try {
            accepter.enroll();
        } catch (ServiceException e) {
            LOGGER.warn(e);
            request.setAttribute(MSG,MSG_SERV_ERR);
            return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.FORWARD);
        }
        LOGGER.info("Enroll done!");
        return new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT);
    }
}
