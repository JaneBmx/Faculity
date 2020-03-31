package com.vlasova.service.impl;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.service.GradeReportService;

import java.util.Map;
import java.util.Set;

public class GradeReportServiceImpl implements GradeReportService {
    private final GradeReportService gradeReportService;

    private static class Holder {
        private static final GradeReportServiceImpl INSTANCE = new GradeReportServiceImpl();
    }

    public static GradeReportServiceImpl getInstance() {
        return GradeReportServiceImpl.Holder.INSTANCE;
    }

    private GradeReportServiceImpl() {
        gradeReportService = GradeReportServiceImpl.getInstance();
    }

    public void addGradeReport(GradeReport gradeReport) {

    }

    public void addGradeReport(int userId, Faculty faculty, double certificate, Map<Subject, Integer> marks) {
        //validate before
    }

    public void removeGradeReport(GradeReport gradeReport){
        //validate before
    }

    public void deleteGradeReport(GradeReport gradeReport){
        //validate before
    }

    public Set<GradeReport> getAllGradeReports(){
        return null;
    }

    public Set<GradeReport> getGradeReportsByFaculty(){
        return null;
    }

    //TODO mb need to add some methods with choosing GradeReports by attestat or privelege
}
