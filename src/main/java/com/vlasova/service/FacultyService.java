package com.vlasova.service;

import com.vlasova.dao.faculty.FacultyDAO;
import com.vlasova.dao.faculty.FacultyDAOImpl;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.comparator.FacultyComparatorById;
import java.util.*;

public class FacultyService {
    private static class Holder {
        static final FacultyService INSTANCE = new FacultyService();
    }

    public static FacultyService getInstance() {
        return Holder.INSTANCE;
    }

    private FacultyService() {
        facultyDAO = new FacultyDAOImpl();
    }

    private final FacultyDAO facultyDAO;

    public void add(Faculty faculty) throws ServiceException {
        try {
            facultyDAO.add(faculty);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void remove(int facultyId) throws ServiceException {
        try {
            facultyDAO.remove(facultyId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(Faculty faculty) throws ServiceException {
        try {
            facultyDAO.update(faculty);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<Faculty> getAll() throws ServiceException {
        try {
            List<Faculty> list = new ArrayList<>(facultyDAO.findAllFaculties());
            list.sort(new FacultyComparatorById());
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Faculty getFacultyById(int id) throws ServiceException {
        try {
            return facultyDAO.findFacultyById(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}