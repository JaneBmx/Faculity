package com.vlasova.controller;

import com.vlasova.controller.command.Answer;
import com.vlasova.controller.command.Command;
import com.vlasova.controller.utill.JSONParser;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.User;
import com.vlasova.dao.exception.connection.ClosePoolException;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.controller.command.CommandType;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.service.FacultyService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.vlasova.controller.command.RequestParams.*;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void init() {
        ConnectionPool.INSTANCE.init();
        LOGGER.info("Controller initialization.");
        try {
            this.getServletContext().setAttribute("faculties", FacultyService.getInstance().getAll());
            LOGGER.info("List of faculties has been loaded.");
        } catch (ServiceException e) {
            LOGGER.warn("Can't load list of faculties", e);
        }
        this.getServletContext().setAttribute("privileges", Privilege.values());
        LOGGER.info("Privileges has been loaded.");
        this.getServletContext().setAttribute("subjects", Subject.values());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    public void destroy() {
        try {
            ConnectionPool.INSTANCE.closePool();
            LOGGER.info("Pool has been closed.");
        } catch (ClosePoolException e) {
            LOGGER.warn("Fail to close pool.", e);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandType.getCommandByString(req.getParameter(COMMAND));
        if(command == null){
            resp.sendError(400);
            return;
        }
        Answer answer = command.execute(req, resp);
        switch (answer.getType()) {
            case REDIRECT:
                resp.sendRedirect(answer.getPageAddress().getPath());
                return;
            case JSON:
                processJSON(req, resp);
                return;
            default:
                req.getRequestDispatcher(answer.getPageAddress().getPath()).forward(req, resp);
        }
    }

    public void processJSON(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json");
        String json = "";
        JSONParser parser = new JSONParser();
        switch (req.getParameter("type")) {
            case USERS:
                json = parser.parseUserListToJSON((List<User>) (req.getAttribute("user_list")));
                break;
            case FACULTY:
                json = parser.parseFacultyListToJSON((List<Faculty>) req.getAttribute("faculty_list"));
                break;
            case GRADE_REPORT:
                json = parser.parseGradeReportListToJSON((List<GradeReport>) req.getAttribute("grade_report_list"));
                break;
        }
        PrintWriter p = resp.getWriter();
        p.write(json);
        p.flush();
    }
}