package com.vlasova.controller.command;

public class Answer {
    private PageAddress pageAddress;
    private Type type;

    public Answer(PageAddress pageAddress, Type type) {
        this.pageAddress = pageAddress;
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    public PageAddress getPageAddress() {
        return pageAddress;
    }

    public void setPageAddress(PageAddress pageAddress) {
        this.pageAddress = pageAddress;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        FORWARD,
        REDIRECT,
        JSON
    }
}