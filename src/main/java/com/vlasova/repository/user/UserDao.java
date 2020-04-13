package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.repository.Dao;

public interface UserDao extends Dao<User> {
    User findUserByLoginAndPassword(String login, String password) throws RepositoryException;

    boolean existsByEmail(String email);
}
