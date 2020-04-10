package com.vlasova.command;

public enum Parameter {
    COMMAND("command"),
    NAME("user_name"),
    SURNAME("user_surname"),
    EMAIL("user_email"),
    LOGIN("user_login"),
    PASSWORD("user_password");

    private String parameter;

    private Parameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
