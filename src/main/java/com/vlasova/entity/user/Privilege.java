package com.vlasova.entity.user;

public enum Privilege {
    COLLEGE(1, "CУЗ"),
    FOREIGN(2, "Иностранный гражданин"),
    RED_DIPLOMA(3, "Диплом/Медаль"),
    ORPHAN(4, "Сирота"),
    OLYMPIAD(5, "Победитель олимпиад"),
    NONE(6, "Нет льгот");

    private int id;
    private String name;

    Privilege(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public static Privilege getPrivilegeById(int id) {
        for (Privilege p : values()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return NONE;
    }
}