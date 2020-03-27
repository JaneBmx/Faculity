package com.vlasova.entity.user;

public enum Privilege {
    NONE(0),
    COLLEGE(1),
    FOREIGN(2),
    RED_DIPLOMA(3),
    ORPHAN(4),
    OLYMPIAD(5);

    private int id;

    private Privilege(int id) {
        this.id = id;
    }

    public static Privilege getPrivilegeById(int id) {
        switch (id) {
            case 1:
                return OLYMPIAD;
            case 2:
                return ORPHAN;
            case 3:
                return RED_DIPLOMA;
            case 4:
                return FOREIGN;
            case 5:
                return COLLEGE;
            default:
                return NONE;
        }
    }
}
