package com.vlasova.service;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.exception.service.UserAlreadyExistsException;
import com.vlasova.exception.service.UserDataNotValidException;
import com.vlasova.repository.user.UserDao;
import com.vlasova.repository.user.UserDaoImpl;
import com.vlasova.specification.user.FindUserById;
import com.vlasova.specification.user.FindUserByLogin;
import com.vlasova.specification.user.FindUserByRole;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static com.vlasova.validation.UserDataValidator.*;

import java.util.HashSet;
import java.util.Set;

public class UserService {
    private static Logger logger = LogManager.getLogger(UserService.class);

    private final UserDao userDao;

    private static class Holder {
        private static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return UserService.Holder.INSTANCE;
    }

    private UserService() {
        userDao = UserDaoImpl.getInstance();
    }

    public void delete(User user) throws ServiceException {
        if (user != null) {
            try {
                userDao.remove(user);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public User logIn(String login, String password) throws ServiceException {
        if (isValidLogin(login) && isValidPassword(password)) {
            try {
                return userDao.findUserByLoginAndPassword(login, password);
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
    public User registration(User user) {
        if (isNotValidUserForRegistration(user)) {
            throw new UserDataNotValidException();
        }
        if (userDao.existsByEmail(user.getEmail())) {
            throw new UserAlreadyExistsException(user.getEmail());
        }

        try {
            userDao.add(user);
            return userDao.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
        } catch (RepositoryException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /*
     *For finding user by common id with GradeReport
     * (for sending email if request accepted)
     */
    public User getUserById(int id) throws ServiceException {
        try {
            Set<User> users = userDao.query(new FindUserById(id));
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
                return userDao.query(new FindUserByRole(role));
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return new HashSet<>();
    }

    /*
     *Check is user exist
     */
    public User getUserByLogin(String login) {
        if (isValidLogin(login)) {
            try {
                Set<User> users = userDao.query(new FindUserByLogin(login));
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
                userDao.update(user);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }
}
