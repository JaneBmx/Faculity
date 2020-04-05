package com.vlasova.command;

public enum PageEnum {
    HOME_PAGE("/index.jsp"),
    LOG_IN("/login.jsp"),
    REGISTRATION("/registration"),
    USER_PAGE("/profile"),

    ERROR("/error_page");
    private String path;

    private PageEnum(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
