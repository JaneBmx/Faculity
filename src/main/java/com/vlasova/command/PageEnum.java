package com.vlasova.command;

public enum PageEnum {
    ACCOUNT_INFO(""),
    ACCOUNT_EDIT(""),
    REGISTRATION(""),
    ADD_REQUEST(""),
    REQUESTS_LIST(""),
    REQUESTR_LIST_ADMIN(""),//a nado li?
    FACULITY_LIST(""),
    FACULITY_INFO(""),
    ERROR_PAGE("");
    private String value;

    private PageEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
