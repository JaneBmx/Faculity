package com.vlasova.command.web;

public enum Parameter {
    COMMAND("command"),
    NAME("name"),
    SURNAME("surname"),
    EMAIL("email"),
    LOGIN("login"),
    PASSWORD("password");

    private String parameter;

    Parameter(String parameter) {
        this.parameter = parameter;
    }

    public String getParameter() {
        return parameter;
    }
}
