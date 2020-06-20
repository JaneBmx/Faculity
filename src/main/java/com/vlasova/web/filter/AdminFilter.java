package com.vlasova.web.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(AdminFilter.class);
    private static final String INDEX_PATH = "INDEX_PATH";
    private String indexPath;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        indexPath = filterConfig.getInitParameter(INDEX_PATH);
    }
    /**
     * Works when somebody trying to get access to admin page
     * Redirecting to start page if role not admin
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String role = (String) httpServletRequest.getSession().getAttribute("role");

        if (role == null || !role.equalsIgnoreCase("admin")) {
            LOGGER.info("AdminFilter: No role. Redirecting to index.jsp");
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + indexPath);
            return;
        }
        LOGGER.info("AdminFilter: Role is admin. Continue...");
        chain.doFilter(request, response);
    }
}
