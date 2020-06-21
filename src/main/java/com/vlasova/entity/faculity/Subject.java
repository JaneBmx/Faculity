package com.vlasova.entity.faculity;

public enum Subject {
    MATH("Математика", 1),
    PHYSICS("Физика", 2),
    GEOGRAPHY("География", 3),
    GEOLOGY("Геология", 4),
    RUSSIAN("Русский язык", 5),
    ENGLISH("Английский язык", 6),
    DEUTSCH("Немецкий язык", 7),
    SPANISH("Испанский язык", 8),
    FRENCH("Французский язык", 9),
    INFORMATICS("Информатика", 10),
    HISTORY("История", 11),
    ART("Искусство", 12),
    CHEMISTRY("Химия", 13),
    BIOLOGY("Биология", 14),
    SOCIAL("Социология", 15);

    private String name;
    private int id;

    Subject(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
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
    return null;
    }
}
