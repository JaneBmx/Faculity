package com.vlasova.service.comparator;

import com.vlasova.entity.user.GradeReport;

import java.util.Comparator;

public class GradeReportByFaculty implements Comparator<GradeReport> {
    @Override
    public int compare(GradeReport o1, GradeReport o2) {
        return o1.getFaculty().getId() - o2.getFaculty().getId();
    }
}
