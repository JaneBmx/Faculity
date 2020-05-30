package com.vlasova.controller;

import com.vlasova.command.Answer;
import com.vlasova.command.Command;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.User;
import com.vlasova.exception.connection.ClosePoolException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.command.CommandType;
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

import static com.vlasova.command.RequestParams.*;

public class Controller extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void init() {
        ConnectionPool.INSTANCE.init();
        LOGGER.info("Controller initialization.");
        try {
            this.getServletContext().setAttribute("faculties", FacultyService.getInstance().getAllFaculties());
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
        Command command = CommandType.valueOf(req.getParameter("command").toUpperCase()).getCommand();
        Answer answer = command.execute(req, resp);


        if (req.getMethod().equals("POST")) {
            resp.sendRedirect(answer.getPageAddress().getPath());
            return;
        }


        if (answer.getType() == Answer.Type.JSON) {
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");

            String json = "";

            switch (req.getParameter("type")){
                case "users":
                    json = parseUserListToJSON((List<User>) req.getAttribute("user_list"));
                    break;
                case "faculty":
                    json = parseFacultyListToJSON((List<Faculty>) req.getAttribute("faculty_list"));
                    break;
                case"grade_report":
                    json = parseGradeReportListToJSON((List<GradeReport>) req.getAttribute("grade_report_list"));
                    break;
            }

            PrintWriter p = resp.getWriter();
            p.write(json);
            p.flush();
        } else {
            req.getRequestDispatcher(answer.getPageAddress().getPath()).forward(req, resp);
        }
    }

    private String parseUserListToJSON(List<User> list) {
        LOGGER.info(list);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User u : list) {
            sb.append("{");
            sb.append("\"id\": \"" + u.getId() + "\",");
            sb.append("\"role\": \"" + u.getRole().name() + "\",");
            sb.append("\"name\": \"" + u.getName() + "\",");
            sb.append("\"surname\": \"" + u.getSurname() + "\",");
            sb.append("\"email\": \"" + u.getEmail() + "\",");
            sb.append("\"login\": \"" + u.getLogin() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    private String parseFacultyListToJSON(List<Faculty> list) {
        LOGGER.info(list);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Faculty f : list) {
            sb.append("{");
            sb.append("\"id\": \"" + f.getId() + "\",");
            sb.append("\"name\": \"" + f.getName() + "\",");

            int count = 1;
            for (Subject s : f.getSubjects()) {
                sb.append("\"sub" + count + "\": \"" + s.getName() + "\",");
                count++;
            }
            sb.append("\"free\": \"" + f.getFreeAcceptPlan() + "\",");
            sb.append("\"paid\": \"" + f.getPaidAcceptPlan() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    private String parseGradeReportListToJSON(List<GradeReport> list) {
        LOGGER.info(list);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (GradeReport g : list) {
            sb.append("{");
            sb.append("\"id\": \"" + g.getId() + "\",");
            sb.append("\"faculty\": \"" + g.getFaculty().getName()+ "\",");
            sb.append("\"isAccept\": \"" + g.isAccepted() + "\",");
            sb.append("\"isFree\": \"" + g.isFree() + "\",");
            sb.append("\"privilege\": \"" + g.getPrivilege().getName() + "\",");
            sb.append("\"attestat\": \"" + g.getAttestatMark() + "\",");
            sb.append("\"average\": \"" + g.getAverageMark() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }
}