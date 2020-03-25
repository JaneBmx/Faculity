package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculity;
import com.vlasova.specification.faculity.FaculitySpecification;

import java.util.Set;

public class FaculityRepositoryImpl implements FaculityRepository {
    /*SQL expressions*/
    private static final String INSERT_FACULITY = "";
    private static final String DELETE_FACULITY = "";
    private static final String UPDATE_FACULITY = "";
    private static final String SELECT_FACULITY = "";

    private static class FaculityRepositoryHolder {
        private static final FaculityRepositoryImpl INSTANCE = new FaculityRepositoryImpl();
    }

    public static FaculityRepositoryImpl getInstance() {
        return FaculityRepositoryHolder.INSTANCE;
    }

    private FaculityRepositoryImpl() {
    }


    @Override
    public void add(Faculity user) {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void update(Faculity user) {

    }

    @Override
    public Set<Faculity> query(FaculitySpecification specification) {
        return null;
    }
}
