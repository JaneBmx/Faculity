package com.vlasova.service.comparator;

import com.vlasova.entity.user.GradeReport;

import java.util.Comparator;

public class GradeReportComparatorByMarks implements Comparator<GradeReport> {
    @Override
    public int compare(GradeReport o1, GradeReport o2) {
        Comparator<GradeReport> comparator = Comparator
                .comparing(GradeReport::getAverageMark)
                .thenComparing(GradeReport::getAttestatMark)
                .thenComparing(GradeReport::getPrivilege);
        return comparator.compare(o1, o2);
    }
}
