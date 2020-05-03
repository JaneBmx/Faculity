package com.vlasova.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(EncodingFilter.class);
    private static final String DEFAULT_ENCODING = "UTF-8";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        request.setCharacterEncoding(DEFAULT_ENCODING);
        chain.doFilter(servletRequest, servletResponse);
        LOGGER.info("Encoding has been change");
    }

    @Override
    public void destroy() {

    }
}
