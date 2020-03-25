package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.specification.user.UserSpecification;

import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    /*SQL expressions*/
    private static final String INSERT_USER = "";
    private static final String DELETE_USER = "";
    private static final String UPDATE_USER = "";
    private static final String SELECT_USER = "";

    private static class UserRepositoryHolder {
        private static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepositoryImpl getInstance() {
        return UserRepositoryImpl.UserRepositoryHolder.INSTANCE;
    }

    private UserRepositoryImpl() {
    }

    @Override
    public void add(User user) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public Set<User> query(UserSpecification specification) {
        return null;
    }
}
