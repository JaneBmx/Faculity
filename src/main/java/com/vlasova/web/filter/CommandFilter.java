package com.vlasova.web.filter;

import com.vlasova.controller.command.CommandType;
import com.vlasova.entity.user.Role;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.EnumSet;
import java.util.Set;
import static com.vlasova.controller.command.CommandType.*;

public class CommandFilter implements Filter {
    private static final Set<CommandType> guest = EnumSet.of(
            SIGN_UP,
            LOGIN,
            SWITCH_LANG,
            GET_COMMON_INFO);
    private static final Set<CommandType> user = EnumSet.of(
            EDIT_REQUEST,
            EDIT_USER,
            LOG_OUT,
            PROFILE,
            SWITCH_LANG,
            GET_COMMON_INFO);
    private static final Set<CommandType> admin = EnumSet.of(
            EDIT_REQUEST,
            EDIT_USER,
            LOG_OUT,
            PROFILE,
            ADD_FACULTY,
            DELETE_FACULTY,
            ADD_USER,
            DELETE_GRADE_REPORT,
            GET_ALL_FACULTIES_AJAX,
            GET_ALL_GRADE_REPORTS_AJAX,
            GET_ALL_USERS_AJAX,
            ACCEPT,
            NULLIFY,
            SWITCH_LANG,
            GET_COMMON_INFO);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        Role role = Role.valueOf(((HttpServletRequest) request).getSession().getAttribute("role").toString().toUpperCase());
        String commandName = req.getParameter("command");

        CommandType command = CommandType.getCommandTypeByString(commandName);

        /**
         * Sending 400 - Bad Request
         * If smth wrong with req args
          */
        if (command == null) {
            resp.sendError(400);
            return;
        }
        boolean access;

        switch (role) {
            case ADMIN:
                access = admin.contains(command);
                break;
            case USER:
                access = user.contains(command);
                break;
            case GUEST:
                access = guest.contains(command);
                break;
            default:
                access = false;
        }

        /**
         * Sending 403 - 403 Forbidden
         * If user has no access for this resource
         */
        if (!access) {
            resp.sendError(403);
            return;
        }

        chain.doFilter(request, response);
    }
}