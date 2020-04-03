package com.vlasova.service.impl;

import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.repository.user.UserRepository;
import com.vlasova.repository.user.UserRepositoryImpl;
import com.vlasova.service.UserService;
import com.vlasova.specification.user.FindUserById;
import com.vlasova.specification.user.FindUserByLogin;
import com.vlasova.specification.user.FindUserByLoginAndPassword;
import com.vlasova.specification.user.FindUserByRole;

import static com.vlasova.validation.UserDataValidator.*;

import java.util.HashSet;
import java.util.Set;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private static class Holder {
        private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    }

    public static UserServiceImpl getInstance() {
        return UserServiceImpl.Holder.INSTANCE;
    }

    private UserServiceImpl() {
        userRepository = UserRepositoryImpl.getInstance();
    }

    public void delete(User user) throws ServiceException {
        if (user != null) {
            try {
                userRepository.remove(user);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public User logIn(String login, String password) throws ServiceException {
        if (isValidLogin(login) && isValidPassword(password)) {
            try {
                Set<User> users = userRepository.query(new FindUserByLoginAndPassword(login, password));
                if (users.iterator().hasNext()) {
                    return users.iterator().next();
                }
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    /*
     *Check is user exist by login,
     * if not: create user, write to DB, get from DB(with id)
     * (id needs for creating gradeReport later)
     */
    public User registration(String name, String surname, String email, String login, String password, Privilege privilege) throws ServiceException {
        User user = null;
        if (isValidName(name) && isValidName(surname) && isValidEmail(email) && isValidLogin(login) && isValidPassword(password)) {
            try {
                if (getUserByLogin(login) == null) {
                    user = new User();
                    user.setRole(Role.ENROLLEE);
                    user.setName(name);
                    user.setSurname(surname);
                    user.setEmail(email);
                    user.setLogin(login);
                    user.setPassword(password);
                    user.setPrivilege(privilege);
                    userRepository.add(user);
                    user = userRepository.query(new FindUserByLoginAndPassword(login, password)).iterator().next();
                }
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return user;
    }

    /*
     *For finding user by common id with GradeReport
     * (for sending email if request accepted)
     */
    public User getUserById(int id) throws ServiceException {
        try {
            Set<User> users = userRepository.query(new FindUserById(id));
            return users.iterator().next();
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    /*
     *For getting all of enrollees(make statistics)
     */
    public Set<User> getUsersByRole(Role role) throws ServiceException {
        if (role != null) {
            try {
                return userRepository.query(new FindUserByRole(role));
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return new HashSet<>();
    }

    /*
     *Check is user exist
     */
    public User getUserByLogin(String login) throws ServiceException {
        if (isValidLogin(login)) {
            try {
                Set<User> users = userRepository.query(new FindUserByLogin(login));
                return users.iterator().next();
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return null;
    }

    public void editUser(User user) throws ServiceException {
        if (isValidUser(user)) {
            try {
                userRepository.update(user);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }
}
