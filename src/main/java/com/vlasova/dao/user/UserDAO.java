package com.vlasova.dao.user;

import com.vlasova.dao.DAO;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;

import java.util.Set;

public interface UserDAO extends DAO<User> {
    boolean existsByEmailAndLogin(String email, String login) throws DAOException;

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    User findUserById(int id) throws DAOException;

    Set<User> findAllUsers() throws DAOException;

    Set<User> findUsersByRole(Role role) throws DAOException;
}
