package com.vlasova.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CommandSecurityFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(CommandSecurityFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        HttpSession session = ((HttpServletRequest) request).getSession();
        Object role = session.getAttribute("role");

        if(role == null){
            LOGGER.info("Role is null. Back to index.jsp.");
            session.setAttribute("role", "guest");
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(httpServletRequest,httpServletResponse);
            return;
        }
        LOGGER.info("Role not null. chain");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
