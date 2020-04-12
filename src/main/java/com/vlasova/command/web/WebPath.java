package com.vlasova.command.web;

public enum WebPath {
    HOME_PAGE("/index.jsp"),
    LOGIN("jsp/login.jsp"),
    REGISTRATION("jsp/registration.jsp"),
    USER_PAGE("jsp/profile.jsp"),

    ERROR("jsp/error_page");
    private String path;

    private WebPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
