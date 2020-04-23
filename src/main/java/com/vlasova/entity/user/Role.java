package com.vlasova.entity.user;

public enum Role {
    ADMIN(1),
    USER(2),
    GUEST(3);

    private int roleId;

    Role(int id) {
        this.roleId = id;
    }

    public int getId() {
        return roleId;
    }

    public static Role getRole(int id) {
        for (Role r : values()) {
            if (r.getId() == id) {
                return r;
            }
        }
        return GUEST;
    }
}