package com.vlasova.controller;

import com.vlasova.command.Command;
import com.vlasova.exception.connection.ClosePoolException;
import com.vlasova.factory.CommandType;
import com.vlasova.params.PageAddress;
import com.vlasova.pool.ConnectionPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/controller"},
        name = "Controller")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 5,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class Controller extends HttpServlet {
    private static Logger LOGGER = LogManager.getLogger(Controller.class);

    @Override
    public void init() {
        LOGGER.info("Controller initialization.");
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
        } catch (ClosePoolException e) {
            LOGGER.warn("Fail to close pool.", e);
        }
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Command command = CommandType.valueOf(req.getParameter("command").toUpperCase()).getCommand();
        PageAddress pageAddress = command.execute(req, resp);
        req.getRequestDispatcher(pageAddress.getPath()).forward(req, resp);
    }
}
