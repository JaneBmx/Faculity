package com.vlasova.factory;

import com.vlasova.service.*;
import com.vlasova.service.impl.FacultyServiceImpl;
import com.vlasova.service.impl.GradeReportServiceImpl;
import com.vlasova.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private final FacultyService facultyService;
    private final GradeReportService gradeReportService;
    private final UserService userService;

    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
    }

    private ServiceFactory() {
        facultyService = FacultyServiceImpl.getInstance();
        gradeReportService = GradeReportServiceImpl.getInstance();
        userService = UserServiceImpl.getInstance();
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
