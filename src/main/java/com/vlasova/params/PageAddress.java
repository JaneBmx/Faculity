package com.vlasova.params;

public enum PageAddress {

    LOG_IN("/jsp/signin.jsp"),
    ADMIN_PAGE("/jsp/admin/admin_page.jsp"),
    USER_PAGE("jsp/user/user_page.jsp"),


    SIGN_UP("/jsp/registration"),

    ABOUT("/jsp/about.jsp"),
    CONTACTS("/jsp/contacts.jsp"),
    HOME_PAGE("/index.jsp"),
    ERROR("/jsp/error_page");
    private String path;

    PageAddress(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
