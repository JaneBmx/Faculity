package com.vlasova.web.listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class Listener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent sessionEvent) {
        sessionEvent.getSession().setAttribute("role", "guest");
        sessionEvent.getSession().setAttribute("lang", "EN");
    }
}