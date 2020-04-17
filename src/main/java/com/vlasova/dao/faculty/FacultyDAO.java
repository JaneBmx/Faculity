package com.vlasova.dao.faculty;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.dao.DAOException;

import java.util.Set;

public interface FacultyDAO extends DAO<Faculty> {
    Set<Faculty> findAllFaculties() throws DAOException;

    Set<Faculty> findFacultyByPaid(boolean isPaid) throws DAOException;

    Faculty findFacultyById(int id) throws DAOException;

    Set<Faculty> findFacultyBySubject(Subject subject) throws DAOException;
}
