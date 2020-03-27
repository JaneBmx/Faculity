package com.vlasova.specification.gradereport;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.specification.QueryException;

import java.util.Set;

public interface GradeReportSpecification {
    Set<GradeReport> query() throws QueryException;
}
