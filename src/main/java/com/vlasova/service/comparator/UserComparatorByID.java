package com.vlasova.service.comparator;

import com.vlasova.entity.user.User;

import java.util.Comparator;

public class UserComparatorByID implements Comparator<User> {
    @Override
    public int compare(User o1, User o2) {
        Comparator<User> comparator = Comparator.comparing(User::getId);
        return comparator.compare(o1, o2);
    }
}
