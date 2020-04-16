package com.vlasova.filter;

import com.vlasova.entity.user.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(dispatcherTypes = {DispatcherType.FORWARD}, urlPatterns = {"/jsp/admin/*"})
public class AdminForwardFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
     *If user not admin, he can't get admin page by url
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        Role role = (Role) httpServletRequest.getSession().getAttribute("role");
        if (role != Role.ADMIN) {
            RequestDispatcher dispatcher = servletRequest.getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
