package com.vlasova.dao.gradereport;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;

import java.util.Collection;

public interface GradeReportDAO extends DAO<GradeReport> {
    Collection<GradeReport> findAllGradeReports() throws DAOException;

    Collection<GradeReport> findGradeReportsByFaculty(Faculty faculty) throws DAOException;

    GradeReport findGradeReportByUser(User user) throws DAOException;

    GradeReport findGradeReportByUserId(int id) throws DAOException;
}
