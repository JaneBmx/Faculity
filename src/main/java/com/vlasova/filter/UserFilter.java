package com.vlasova.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageSecurityFilter implements Filter {
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

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+indexPath);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
