package com.vlasova.service;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.gradereport.GradeReportDAOImpl;
import com.vlasova.service.comparator.GradeReportComparatorByID;

import java.util.*;

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

    @Deprecated
    public void deleteGradeReport(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.remove(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void deleteGradeReport(int gradeReportId) throws ServiceException {
        try {
            gradeReportDAO.remove(gradeReportId);
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

    public List<GradeReport> getAllGradeReports() throws ServiceException {
        try {
            List<GradeReport> list = new ArrayList<>(gradeReportDAO.findAllGradeReports());
            list.sort(new GradeReportComparatorByID());
            return list;
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public List<GradeReport> getGradeReportsByFaculty(Faculty faculty) throws ServiceException {
        try {
            List<GradeReport> list = new ArrayList<>(gradeReportDAO.findGradeReportsByFaculty(faculty));
            list.sort(new GradeReportComparatorByID());
            return list;
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Deprecated
    public GradeReport getGradeReportByUser(User user) throws ServiceException {
        try {
            return gradeReportDAO.findGradeReportByUser(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public GradeReport getGradeReportByUserId(int id) throws ServiceException {
        try {
            return gradeReportDAO.findGradeReportByUserId(id);
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