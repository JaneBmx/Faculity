package com.vlasova.specification.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.specification.QueryException;

import java.util.Set;

public interface UserSpecification {
    Set<User> query() throws QueryException;
}
