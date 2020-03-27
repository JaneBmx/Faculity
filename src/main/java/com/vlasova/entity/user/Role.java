package com.vlasova.entity.user;

public enum Role {
    ADMIN(1),
    PROFESSOR(2),
    ENROLLEE(3),
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
                return PROFESSOR;
            case 3:
                return ENROLLEE;
            default:
                return GUEST;
        }
    }
}