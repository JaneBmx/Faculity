package com.vlasova.entity.user;

public enum Privilege {
    NONE(6),
    COLLEGE(1),
    FOREIGN(2),
    RED_DIPLOMA(3),
    ORPHAN(4),
    OLYMPIAD(5);

    private int id;

    Privilege(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
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
