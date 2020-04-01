package com.vlasova.service.impl;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.repository.gradereport.GradeReportRepository;
import com.vlasova.repository.gradereport.GradeReportRepositoryImpl;
import com.vlasova.service.GradeReportService;

import static com.vlasova.validation.GradeReportValidator.*;
import static com.vlasova.validation.UserDataValidator.*;

import java.util.Map;
import java.util.Set;

public class GradeReportServiceImpl implements GradeReportService {
    private final GradeReportRepository gradeReportRepository;
    private final GradeReportService gradeReportService;

    private static class Holder {
        private static final GradeReportServiceImpl INSTANCE = new GradeReportServiceImpl();
    }

    public static GradeReportServiceImpl getInstance() {
        return GradeReportServiceImpl.Holder.INSTANCE;
    }

    private GradeReportServiceImpl() {
        gradeReportService = GradeReportServiceImpl.getInstance();
        gradeReportRepository = GradeReportRepositoryImpl.getInstance();
    }

    public void addGradeReport(GradeReport gradeReport) {

    }

    public void addGradeReport(int userId, Faculty faculty, double certificate, Map<Subject, Integer> marks) {
        //validate before
    }

    public void removeGradeReport(GradeReport gradeReport) {
        //validate before
    }

    public void deleteGradeReport(GradeReport gradeReport) {
        //validate before
    }

    public Set<GradeReport> getAllGradeReports() {
        return null;
    }

    public Set<GradeReport> getGradeReportsByFaculty() {
        return null;
    }

    public void setGradeReportAcceptAndFree(User user, GradeReport gradeReport, boolean isAccepted, boolean isFree)
            throws ServiceException {
        if (isValidGradeReport(gradeReport) && user != null
                && gradeReport.getId() == user.getId()) {
            gradeReport.setAccepted(isAccepted);
            gradeReport.setFree(isFree);
            try {
                gradeReportRepository.update(user, gradeReport);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }
    //TODO mb need to add some methods with choosing GradeReports by attestat or privelege
    //privelege will be add in some class with sorting?
}
