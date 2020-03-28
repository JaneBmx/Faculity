package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.repository.Repository;
import com.vlasova.specification.user.UserSpecification;

import java.util.Set;

public interface UserRepository extends Repository {
    void add(User user) throws RepositoryException;

    void remove(int id) throws RepositoryException;

    void update(User user) throws RepositoryException;

    Set<User> query(UserSpecification specification) throws RepositoryException;
}
