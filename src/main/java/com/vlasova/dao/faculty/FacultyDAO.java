package com.vlasova.dao.faculty;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.dao.exception.dao.DAOException;
import java.util.Collection;
import java.util.Map;

public interface FacultyDAO extends DAO<Faculty> {
    Collection<Faculty> findAllFaculties() throws DAOException;

    Faculty findFacultyById(int id) throws DAOException;

    boolean isExistByName(String name) throws DAOException;

    Map<String, Integer> getCommonStatistics() throws DAOException;
}