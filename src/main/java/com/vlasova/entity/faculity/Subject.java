package com.vlasova.entity.faculity;

public enum Subject {
    MATH("Mathematics", 1),
    PHYSICS("Physics", 2),
    GEOGRAPHY("Geography", 3),
    GEOLOGY("Geology", 4),
    RUSSIAN("Russian", 5),
    ENGLISH("English", 6),
    DEUTCH("Deutch", 7),
    SPANISH("Spanish", 8),
    FRENCH("French", 9),
    INFORMATICS("Informatics", 10),
    HISTORY("History", 11),
    ART("Art", 12),
    CHEMISTRY("Chemistry", 13),
    BIOLOGY("Biology", 14),
    SOCIAL("Social", 15),
    UNDEFINED("Undefined", 0);
    private String value;
    private int id;

    Subject(String value, int id) {
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
        for (Subject s : values()) {
            if (s.getId() == id) {
                return s;
            }
        }
        return UNDEFINED;
    }
}
