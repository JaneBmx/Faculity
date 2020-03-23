package com.vlasova.repository;

import com.vlasova.entity.Gradereport;
import com.vlasova.specification.GradereportSpecification;

import java.util.Set;

public interface GradereportRepository {
    void add(Gradereport user);

    void remove(int id);

    void update(Gradereport user);

    Set<Gradereport> query(GradereportSpecification specification);
}
