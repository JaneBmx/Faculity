package com.vlasova.controller.command.impl.page;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.controller.command.web.PageAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static com.vlasova.controller.command.RequestParams.*;

public class ProfilePageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ProfilePageCommand.class);

    @Override
    public Answer execute(HttpServletRequest request, HttpServletResponse response) {
        String role = (String) request.getSession().getAttribute("role");
        if (role != null && !role.equalsIgnoreCase(GUEST)) {
            LOGGER.info("Role has been found. Go to user page.");
            return request.getSession().getAttribute(ROLE).toString().equalsIgnoreCase(ADMIN)
                    ? new Answer(PageAddress.ADMIN_PAGE, Answer.Type.REDIRECT)
                    : new Answer(PageAddress.USER_PAGE, Answer.Type.REDIRECT);
        }
        LOGGER.info("Role has't been found. Return to login page.");
        return new Answer(PageAddress.LOG_IN, Answer.Type.FORWARD);
    }
}