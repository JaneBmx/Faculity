package com.vlasova.command.web;

public enum PageEnum {
    HOME_PAGE("/index.jsp"),
    LOGIN("login.jsp"),
    REGISTRATION("registration.jsp"),
    USER_PAGE("profile.jsp"),

    ERROR("error_page");
    private String path;

    PageEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
