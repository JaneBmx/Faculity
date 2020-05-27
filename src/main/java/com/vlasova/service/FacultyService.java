package com.vlasova.service;

import com.vlasova.dao.faculty.FacultyDAO;
import com.vlasova.dao.faculty.FacultyDAOImpl;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;

import java.util.*;

public class FacultyService {
    private static class Holder {
        static final FacultyService INSTANCE = new FacultyService();
    }

    public static FacultyService getInstance() {
        return Holder.INSTANCE;
    }

    private FacultyService(){
        facultyDAO = new FacultyDAOImpl();
    }

    private final FacultyDAO facultyDAO;

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

    public void addFaculty(Faculty faculty) throws ServiceException {
        try{
            facultyDAO.add(faculty);
        }catch (DAOException e ){
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

    public List<Faculty> getAllFaculties() throws ServiceException {
        try {
            return new ArrayList<>(facultyDAO.findAllFaculties());
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Faculty> getAllFreePaidFaculties(boolean isPaid) throws ServiceException {
        try {
            return new HashSet<>(facultyDAO.findFacultyByPaid(isPaid));
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
                return new HashSet<>(facultyDAO.findFacultyBySubject(subject));
            } catch (DAOException e) {
                throw new ServiceException(e);
            }
        }
        return new HashSet<>();
    }
}