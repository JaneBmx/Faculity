package com.vlasova.entity.faculity;

public enum Subject {
    MATH("Mathematics", 1),
    PHYSICS("Physics", 2),
    GEOGRAPHY("Geography", 3),
    RUSSIAN("Russian language", 4),
    ENGLISH("English language", 5),
    CHEMISTRY("Chemistry", 6);
    private String value;
    private int id;

    private Subject(String value, int id) {
        this.value = value;
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public int getId() {
        return id;
    }
}
