package com.vlasova.service;

import com.vlasova.dao.user.UserDAO;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.user.UserDAOImpl;

import java.util.HashSet;
import java.util.Set;

public class UserService {
    public static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    private UserService() {
        userDAO = new UserDAOImpl();
    }

    private final UserDAO userDAO;

    public User registration(User user) throws ServiceException {
        try {
            if (!userDAO.existsByEmailAndLogin(user.getEmail(), user.getPassword())) {
                userDAO.add(user);
                user = userDAO.findUserByLoginAndPassword(user.getLogin(),user.getPassword());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public void delete(User user) throws ServiceException {
        try {
            userDAO.remove(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User logIn(String login, String password) throws ServiceException {
        try {
            return userDAO.findUserByLoginAndPassword(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public User getUserById(int id) throws ServiceException {
        try {
            return userDAO.findUserById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<User> getUsersByRole(Role role) throws ServiceException {
        try {
            return new HashSet<>(userDAO.findUsersByRole(role));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void editUser(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
