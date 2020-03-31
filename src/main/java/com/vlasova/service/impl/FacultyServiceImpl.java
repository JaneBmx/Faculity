package com.vlasova.service.impl;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.repository.faculity.FacultyRepository;
import com.vlasova.repository.faculity.FacultyRepositoryImpl;
import com.vlasova.service.FacultyService;
import com.vlasova.specification.faculity.FindAllFaculties;
import com.vlasova.specification.faculity.FindFacultyByFreePaid;
import com.vlasova.specification.faculity.FindFacultyById;
import com.vlasova.specification.faculity.FindFacultyBySubject;
import com.vlasova.valodation.ServiceValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FacultyServiceImpl implements FacultyService {
    private static Set<Faculty> faculties = new HashSet<>();
    private final FacultyRepository facultyRepository;

    //TODO add to Faculties!
    public FacultyServiceImpl() {
        facultyRepository = FacultyRepositoryImpl.getInstance();
    }

    public Set<Faculty> getAllFaculties() throws ServiceException {
        try {
            return facultyRepository.query(new FindAllFaculties());
        } catch (QueryException | RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Faculty> getAllFreePaidFaculties() throws ServiceException {
        try {
            return facultyRepository.query(new FindFacultyByFreePaid());
        } catch (QueryException | RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Set<Faculty> getFacultiesBySubjects(Set<Subject> subjects) throws ServiceException {
        try {
            return facultyRepository.query(new FindFacultyBySubject(subjects));
        } catch (QueryException | RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public Faculty getFacultyById(int id) throws ServiceException {
        try {
            return facultyRepository.query(new FindFacultyById(id)).iterator().next();
        } catch (QueryException | RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    public void addFaculty(Faculty faculty) throws ServiceException {
        if (faculty != null) {
            faculties.add(faculty);
            try {
                facultyRepository.add(faculty);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public void addFaculty(String name, int free, int paid, Subject... subjects) throws ServiceException {
        if (ServiceValidator.isValidFaculty(name, free, paid, subjects)) {
            Faculty faculty = new Faculty();
            faculty.setName(name);
            faculty.setFreeAcceptPlan(free);
            faculty.setPaidAcceptPlan(paid);
            faculty.setSubjects(new HashSet<Subject>(Arrays.asList(subjects)));
            try {
                faculties.add(faculty);
                facultyRepository.add(faculty);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public void deleteFaculty(Faculty faculty) throws ServiceException {
        if (faculty != null) {
            try {
                facultyRepository.remove(faculty.getId());
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public void updateFaculty(Faculty faculty) throws ServiceException {
        if (faculty != null) {
            try {
                facultyRepository.update(faculty);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }
}