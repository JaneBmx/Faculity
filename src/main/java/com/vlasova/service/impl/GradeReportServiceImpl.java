package com.vlasova.service.impl;
import com.vlasova.service.GradeReportService;

public class GradeReportServiceImpl implements GradeReportService {
    private final GradeReportService gradeReportService;

    private static class Holder{
        private static final  GradeReportServiceImpl INSTANCE = new GradeReportServiceImpl();
    }

    public static GradeReportServiceImpl getInstance(){
        return GradeReportServiceImpl.Holder.INSTANCE;
    }

    private GradeReportServiceImpl(){
        gradeReportService = GradeReportServiceImpl.getInstance();
    }

    //TODO add methods
}
