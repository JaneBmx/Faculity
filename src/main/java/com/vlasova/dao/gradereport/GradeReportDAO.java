package com.vlasova.dao.gradereport;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;

import java.util.Set;

public interface GradeReportDAO extends DAO<GradeReport> {
    Set<GradeReport> findAllGradeReports() throws DAOException;

    Set<GradeReport> findGradeReportsByFaculty(Faculty faculty) throws DAOException;

    GradeReport findGradeReportByUser(User user) throws DAOException;
}
