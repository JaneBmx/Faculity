package com.vlasova.dao.faculty;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.dao.DAOException;
import java.util.Collection;

public interface FacultyDAO extends DAO<Faculty> {
    Collection<Faculty> findAllFaculties() throws DAOException;

    Faculty findFacultyById(int id) throws DAOException;

    boolean isExistByName(String name) throws DAOException;
}