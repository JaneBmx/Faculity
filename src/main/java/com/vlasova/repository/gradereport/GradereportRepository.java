package com.vlasova.repository.gradereport;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.repository.Repository;
import com.vlasova.specification.gradereport.GradeReportSpecification;

import java.util.Set;

public interface GradeReportRepository extends Repository {
    void add(User user) throws RepositoryException;

    void remove(User user) throws RepositoryException;

    void update(User user, GradeReport gradeReport) throws RepositoryException;

    Set<GradeReport> query(GradeReportSpecification specification) throws RepositoryException;
}