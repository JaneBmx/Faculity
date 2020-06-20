package com.vlasova.service;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.dao.exception.dao.DAOException;
import com.vlasova.service.exception.ServiceException;
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

    public void add(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.add(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void remove(int id) throws ServiceException {
        try {
            gradeReportDAO.remove(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public void update(GradeReport gradeReport) throws ServiceException {
        try {
            gradeReportDAO.update(gradeReport);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    public List<GradeReport> getAll() throws ServiceException {
        try {
            List<GradeReport> list = new ArrayList<>(gradeReportDAO.findAllGradeReports());
            list.sort(new GradeReportComparatorByID());
            return list;
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public GradeReport getGradeReportByUserId(int id) throws ServiceException {
        try {
            return gradeReportDAO.findGradeReportByUserId(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}