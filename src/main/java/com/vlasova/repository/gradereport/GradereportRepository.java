package com.vlasova.repository.gradereport;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.specification.gradereport.GradeReportSpecification;

import java.util.Set;

public interface GradereportRepository {
    void add(GradeReport user);

    void remove(int id);

    void update(GradeReport user);

    Set<GradeReport> query(GradeReportSpecification specification);
}
