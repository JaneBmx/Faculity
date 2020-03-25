package com.vlasova.entity.user;

public enum Role {
    ADMIN(1),
    PREPOD(2),
    ABITUR(3), //USER
    GUEST(4);
    private int roleId;

    private Role(int id) {
        this.roleId = id;
    }

    public int getRoleId() {
        return roleId;
    }

    public static Role getRole(int id) {
        switch (id) {
            case 1:
                return ADMIN;
            case 2:
                return PREPOD;
            case 3:
                return ABITUR;
            default:
                return GUEST;
        }
    }
}