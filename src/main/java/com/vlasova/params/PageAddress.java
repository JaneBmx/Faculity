package com.vlasova.params;

public enum PageAddress {
    HOME_PAGE("/index.jsp"),
    LOG_IN("/jsp/signin.jsp"),
    SIGN_UP("/jsp/registration"),
    USER_PAGE("jsp/user/user.jsp"),
    ADMIN_PAGE("/jsp/admin/admin.jsp"),
    ABOUT("/jsp/about.jsp"),
    CONTACTS("/jsp/contacts.jsp"),

    ERROR("/jsp/error_page");
    private String path;

    PageAddress(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
