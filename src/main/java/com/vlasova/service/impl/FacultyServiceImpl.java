package com.vlasova.service.impl;

import com.vlasova.repository.faculity.FacultyRepository;
import com.vlasova.repository.faculity.FacultyRepositoryImpl;
import com.vlasova.service.FacultyService;

public class FacultyServiceImpl implements FacultyService {
    private final FacultyRepository facultyRepository;

    private static class Holder{
        private static final  FacultyServiceImpl INSTANCE = new FacultyServiceImpl();
    }

    public static FacultyServiceImpl getInstance(){
        return Holder.INSTANCE;
    }

    private FacultyServiceImpl(){
        facultyRepository = FacultyRepositoryImpl.getInstance();
    }

    //TODO add methods
}