package com.vlasova.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.*;

public class EncodingFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger(EncodingFilter.class);
    private static final String ENCODING = "encoding";
    private String code;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        code = filterConfig.getInitParameter(ENCODING);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String codeRequest = request.getCharacterEncoding();
        if (code != null && !code.equalsIgnoreCase(codeRequest)) {
            request.setCharacterEncoding(code);
            response.setCharacterEncoding(code);
            LOGGER.info("Encoding has been change.");
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        code = null;
    }
}