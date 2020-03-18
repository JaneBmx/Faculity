package com.vlasova.entity.faculity;

public enum Subject {
    MATH("Mathematics"),
    PHYSICS("Physics"),
    GEOGRAPHY("Geography"),
    RUSSIAN("Russian language"),
    ENGLISH("English language"),
    CHEMISTRY("Chemistry");
    private String value;

    private Subject(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
