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

    public static Subject getSubjectById(int id) {
        switch (id) {
            case 1:
                return MATH;
            case 2:
                return PHYSICS;
            case 3:
                return GEOGRAPHY;
            case 4:
                return RUSSIAN;
            case 5:
                return ENGLISH;
            case 6:
                return CHEMISTRY;
            default:
                return null;
        }
    }
}
