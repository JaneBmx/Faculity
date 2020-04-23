package com.vlasova.service;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.dao.gradereport.GradeReportDAOImpl;

import java.util.Map;
import java.util.Set;

public enum GradeReportService {
    SERVICE;
    private GradeReportDAO gradeReportDAO = new GradeReportDAOImpl();

    public void addGradeReport(int userId, Faculty faculty, double certificate, Map<Subject, Integer> marks) throws ServiceException {
        GradeReport gradeReport = new GradeReport();
        gradeReport.setId(userId);
        gradeReport.setFacultyId(faculty.getId());
        gradeReport.setAccepted(false);
        gradeReport.setFree(false);
        gradeReport.setMarks(marks);
        gradeReport.setAttestatMark(certificate);
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
            return gradeReportDAO.findAllGradeReports();
        } catch (DAOException e) {
            throw new ServiceException();
        }
    }

    public Set<GradeReport> getGradeReportsByFaculty(Faculty faculty) throws ServiceException {
        try {
            return gradeReportDAO.findGradeReportsByFaculty(faculty);
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
