package com.vlasova.factory;

import com.vlasova.service.FacultyService;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.UserService;

public final class ServiceFactory {
    private final FacultyService facultyService;
    private final GradeReportService gradeReportService;
    private final UserService userService;

    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {
        facultyService = FacultyService.SERVICE;
        gradeReportService = GradeReportService.SERVICE;
        userService = UserService.SERVICE;
    }

    public static ServiceFactory getInstance() {
        return Holder.INSTANCE;
    }

    public FacultyService getFacultyService() {
        return facultyService;
    }

    public GradeReportService getGradeReportService() {
        return gradeReportService;
    }

    public UserService getUserService() {
        return userService;
    }
}
