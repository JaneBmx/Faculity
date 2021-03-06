package com.vlasova.service;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.dao.gradereport.GradeReportDAOImpl;
import com.vlasova.dao.user.UserDAO;
import com.vlasova.entity.user.User;
import com.vlasova.dao.exception.dao.DAOException;
import com.vlasova.service.exception.ServiceException;
import com.vlasova.dao.user.UserDAOImpl;
import com.vlasova.service.comparator.UserComparatorByID;
import java.util.*;

public class UserService {
    private static class Holder {
        static final UserService INSTANCE = new UserService();
    }

    public static UserService getInstance() {
        return Holder.INSTANCE;
    }

    private UserService() {
        userDAO = new UserDAOImpl();
        gradeReportDAO = new GradeReportDAOImpl();
    }

    private final UserDAO userDAO;
    private final GradeReportDAO gradeReportDAO;

    public User registrateAndLogin(User user) throws ServiceException {
        try {
            if (userDAO.existsByEmailAndLogin(user.getEmail(), user.getLogin())) {
                userDAO.add(user);
                user = userDAO.findUserByLoginAndPassword(user.getLogin(), user.getPassword());
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return user;
    }

    public void registrate(User user) throws ServiceException {
        try{
            if(userDAO.existsByEmailAndLogin(user.getEmail(), user.getLogin())){
                userDAO.add(user);
            }
        }catch (DAOException e){
            throw new ServiceException(e);
        }
    }

    public void remove(int id) throws ServiceException {
        try {
            gradeReportDAO.remove(id);
            userDAO.remove(id);
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

    public void update(User user) throws ServiceException {
        try {
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<User> getAll() throws ServiceException {
        try {
            List<User> list = new ArrayList<>(userDAO.findAllUsers());
            list.sort(new UserComparatorByID());
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}