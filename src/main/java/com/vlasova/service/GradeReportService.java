package com.vlasova.service;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.gradereport.GradeReportDAOImpl;

import java.util.HashSet;
import java.util.Set;

public class GradeReportService {
    private static class Holder {
        static final GradeReportService INSTANCE = new GradeReportService();
    }

    public static GradeReportService getInstance() {
        return Holder.INSTANCE;
    }

    private final GradeReportDAO gradeReportDAO;

    private GradeReportService() {
        gradeReportDAO = new GradeReportDAOImpl();
    }

    public void addGradeReport(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.add(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteGradeReport(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.remove(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void updateGradeReport(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.update(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public Set<GradeReport> getAllGradeReports() throws ServiceException {
        try {
            return new HashSet<>(gradeReportDAO.findAllGradeReports());
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public Set<GradeReport> getGradeReportsByFaculty(Faculty faculty) throws ServiceException {
        try {
            return new HashSet<>(gradeReportDAO.findGradeReportsByFaculty(faculty));
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public GradeReport getGradeReportByUser(User user) throws ServiceException {
        try {
            return gradeReportDAO.findGradeReportByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void setGradeReportAcceptAndFree(GradeReport gradeReport, boolean isAccepted, boolean isFree)
            throws ServiceException {
        gradeReport.setAccepted(isAccepted);
        gradeReport.setFree(isFree);
        try {
            gradeReportDAO.update(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}