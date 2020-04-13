package com.vlasova.service;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.repository.gradereport.GradeReportDao;
import com.vlasova.repository.gradereport.GradeReportDaoImpl;
import com.vlasova.specification.gradereport.FindAllGradeReports;
import com.vlasova.specification.gradereport.FindGradeReportByFaculty;

import static com.vlasova.validation.GradeReportValidator.*;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GradeReportService {
    private final GradeReportDao gradeReportRepository;

    private static class Holder {
        private static final GradeReportService INSTANCE = new GradeReportService();
    }

    public static GradeReportService getInstance() {
        return GradeReportService.Holder.INSTANCE;
    }

    private GradeReportService() {
        gradeReportRepository = GradeReportDaoImpl.getInstance();
    }

    public void addGradeReport(int userId, Faculty faculty, double certificate, Map<Subject, Integer> marks) throws ServiceException {
        if (isValidGradeReport(faculty, certificate, marks)) {
            GradeReport gradeReport = new GradeReport();
            gradeReport.setId(userId);
            gradeReport.setFacultyId(faculty.getId());
            gradeReport.setAccepted(false);
            gradeReport.setFree(false);
            gradeReport.setMarks(marks);
            gradeReport.setCertificate(certificate);
            try {
                gradeReportRepository.add(gradeReport);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public void deleteGradeReport(GradeReport gradeReport) throws ServiceException {
        if (isValidGradeReport(gradeReport)) {
            try {
                gradeReportRepository.remove(gradeReport);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public Set<GradeReport> getAllGradeReports() throws ServiceException {
        try {
            return gradeReportRepository.query(new FindAllGradeReports());
        } catch (RepositoryException e) {
            throw new ServiceException();
        }
    }

    public Set<GradeReport> getGradeReportsByFaculty(Faculty faculty) throws ServiceException {
        if (faculty != null) {
            try {
                gradeReportRepository.query(new FindGradeReportByFaculty(faculty));
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
        return new HashSet<>();
    }

    public void setGradeReportAcceptAndFree(GradeReport gradeReport, boolean isAccepted, boolean isFree)
            throws ServiceException {
        if (isValidGradeReport(gradeReport)) {
            gradeReport.setAccepted(isAccepted);
            gradeReport.setFree(isFree);
            try {
                gradeReportRepository.update(gradeReport);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }

    public void updateGradeReport(GradeReport gradeReport) throws ServiceException {
        if (isValidGradeReport(gradeReport)) {
            try {
                gradeReportRepository.update(gradeReport);
            } catch (RepositoryException e) {
                throw new ServiceException(e);
            }
        }
    }
}
