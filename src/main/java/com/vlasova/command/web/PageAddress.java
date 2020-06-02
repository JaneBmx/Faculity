package com.vlasova.command.web;

public enum PageAddress {
    LOG_IN      ("/jsp/signin.jsp"),
    ADMIN_PAGE  ("/jsp/admin/admin_page.jsp"),
    USER_PAGE   ("jsp/user/user_page.jsp"),
    SIGN_UP     ("/jsp/signup.jsp"),

    ABOUT       ("/jsp/about.jsp"),
    CONTACTS    ("/jsp/contacts.jsp"),
    HOME_PAGE   ("/index.jsp"),
    ERROR       ("/jsp/error_page");

    private String path;

    PageAddress(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}