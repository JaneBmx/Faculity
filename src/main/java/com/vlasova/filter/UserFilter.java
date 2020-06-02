package com.vlasova.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(UserFilter.class);
    private static final String INDEX_PATH = "INDEX_PATH";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(INDEX_PATH);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String role = (String) httpServletRequest.getSession().getAttribute("role");

        if (role == null || role.equalsIgnoreCase("guest")) {
            LOGGER.info("UserFilter: No role. Redirecting to index.jsp");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
            return;
        }
        LOGGER.info("UserFilter: Role found. Continue...");
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
