package com.vlasova.dao.faculty;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.dao.DAOException;

import java.util.Collection;

public interface FacultyDAO extends DAO<Faculty> {
    Collection<Faculty> findAllFaculties() throws DAOException;

    Collection<Faculty> findFacultyByPaid(boolean isPaid) throws DAOException;

    Faculty findFacultyById(int id) throws DAOException;

    Collection<Faculty> findFacultyBySubject(Subject subject) throws DAOException;
}
