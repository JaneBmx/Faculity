package com.vlasova.validation;

import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.faculity.Faculties;

import java.util.Map;

public class GradeReportValidator {
    private static final int MIN_MARK = 1;

    private GradeReportValidator() {
    }

    public static boolean isValidGradeReport(GradeReport gradeReport) {
        if (gradeReport != null) {//TODO how to check is this user exist?
            return Faculties.FACULTIES.isExist(gradeReport.getFaculty())
                    && gradeReport.getCertificate() >= MIN_MARK && isValidMarks(gradeReport.getMarks());
        }
        return false;
    }

    public static boolean isValidMarks(Map<Subject, Integer> marks) {
        if (marks != null && !marks.isEmpty()) {
            for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
                if (entry.getKey() == null || entry.getValue() < MIN_MARK) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
}
