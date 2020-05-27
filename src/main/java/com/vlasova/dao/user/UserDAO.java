package com.vlasova.dao.user;

import com.vlasova.dao.DAO;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;

import java.util.Collection;

public interface UserDAO extends DAO<User> {
    boolean existsByEmailAndLogin(String email, String login) throws DAOException;

    User findUserByLoginAndPassword(String login, String password) throws DAOException;

    User findUserById(int id) throws DAOException;

    Collection<User> findAllUsers() throws DAOException;

    Collection<User> findUsersByRole(Role role) throws DAOException;

    void remove(int userId) throws DAOException;
}
