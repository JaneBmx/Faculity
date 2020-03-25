package com.vlasova.specification.user;

import com.vlasova.entity.user.User;

import java.util.Set;

public interface UserSpecification {
    Set<User> query();
}
