package com.vlasova.repository.gradereport;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.repository.faculity.FaculityRepositoryImpl;
import com.vlasova.specification.GradereportSpecification;

import java.util.Set;

public class GradereportRepositoryImpl implements GradereportRepository {
    /*SQL expressions*/
    private static final String INSERT_GRADEREPORT = "";
    private static final String DELETE_GRADEREPORT = "";
    private static final String UPDATE_GRADEREPORT = "";
    private static final String SELECT_GRADEREPORT = "";

    private static class GradereportRepositoryHolder {
        private static final GradereportRepositoryImpl INSTANCE = new GradereportRepositoryImpl();
    }

    public static GradereportRepositoryImpl getInstance() {
        return GradereportRepositoryImpl.GradereportRepositoryHolder.INSTANCE;
    }

    private GradereportRepositoryImpl() {
    }

    @Override
    public void add(GradeReport user) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(GradeReport user) {

    }

    @Override
    public Set<GradeReport> query(GradereportSpecification specification) {
        return null;
    }
}
