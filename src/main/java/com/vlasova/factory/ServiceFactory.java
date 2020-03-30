package com.vlasova.factory;

import com.vlasova.service.*;
import com.vlasova.service.impl.FacultyServiceImpl;
import com.vlasova.service.impl.GradeReportServiceImpl;
import com.vlasova.service.impl.UserServiceImpl;

public final class ServiceFactory {
    private final FacultyService facultyService = new FacultyServiceImpl();
    private final GradeReportService gradeReportService = new GradeReportServiceImpl();
    private final UserService userService = new UserServiceImpl();

    private static class Holder {
        private static final ServiceFactory INSTANCE = new ServiceFactory();
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
