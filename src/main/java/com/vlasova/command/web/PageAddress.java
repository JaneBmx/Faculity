package com.vlasova.command.web;

public enum PageAddress {
    ERROR("jsp/error_page"),
    HOME_PAGE("jsp/home.jsp"),
    LOGIN("jsp/login.jsp"),
    REGISTRATION("registration.jsp"),
    ABOUT("jsp/about.jsp"),
    CONTACRS("jsp/contacts"),
    USER_PAGE("user_page.jsp");
    private String path;

    PageAddress(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}