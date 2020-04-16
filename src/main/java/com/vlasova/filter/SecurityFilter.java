package com.vlasova.filter;

import com.vlasova.entity.user.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/controller"},
        servletNames = {"Controller"})
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /*
     *Avoid getting authorized pages without login by url
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        Role role = (Role) session.getAttribute("role");
        if (role == null) {
            role = Role.GUEST;
            RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/index.jsp");
            //TODO add path
            dispatcher.forward(request, response);
            return;
        }
        //path the request along the filter chain
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
