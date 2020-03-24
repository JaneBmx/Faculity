package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.repository.Repository;
import com.vlasova.specification.UserSpecification;

import java.util.Set;

public interface UserRepository extends Repository {
    void add(User user);

    void remove(int id);

    void update(User user);

    Set<User> query(UserSpecification specification);
}