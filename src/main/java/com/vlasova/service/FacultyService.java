package com.vlasova.service;

import com.vlasova.dao.faculty.FacultyDAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.faculty.FacultyDAOImpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum FacultyService {
    SERVICE;
    private FacultyDAO facultyDAO = new FacultyDAOImpl();

    public void addFaculty(String name, int free, int paid, Subject... subjects) throws ServiceException {
        Faculty faculty = new Faculty();
        faculty.setName(name);
        faculty.setFreeAcceptPlan(free);
        faculty.setPaidAcceptPlan(paid);
        faculty.setSubjects(new HashSet<>(Arrays.asList(subjects)));
        try {
            facultyDAO.add(faculty);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteFaculty(Faculty faculty) throws ServiceException {
        try {
            facultyDAO.remove(faculty);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateFaculty(Faculty faculty) throws ServiceException {
        try {
            facultyDAO.update(faculty);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Faculty> getAllFaculties() throws ServiceException {
        try {
            return facultyDAO.findAllFaculties();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Faculty> getAllFreePaidFaculties(boolean isPaid) throws ServiceException {
        try {
            return facultyDAO.findFacultyByPaid(isPaid);
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

    public Set<Faculty> getFacultiesBySubjects(Subject subject) throws ServiceException {
        if (subject != null) {
            try {
                return facultyDAO.findFacultyBySubject(subject);
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return new HashSet<>();
    }
}