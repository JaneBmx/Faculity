package com.vlasova.service;

import com.vlasova.dao.user.UserDAO;
import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.user.UserDAOImpl;

import java.util.Set;

public enum UserService {
    SERVICE;
    private final UserDAO userDAO = new UserDAOImpl();

    public User registration(String name, String surname, String email, String login, String password, Privilege privilege) throws ServiceException {
        User user = null;
        try {
            if (!userDAO.existsByEmailAndLogin(email, login)) {
                user = new User();
                user.setRole(Role.USER);
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setLogin(login);
                user.setPassword(password);
                user.setPrivilege(privilege);
                userDAO.add(user);
                user = userDAO.findUserByLoginAndPassword(login, password);
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
            return userDAO.findUsersByRole(role);
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
