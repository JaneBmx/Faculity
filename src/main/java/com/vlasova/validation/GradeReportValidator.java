package com.vlasova.validation;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;

import java.util.Map;

public class GradeReportValidator {
    private static final int MIN_MARK = 1;
    private static final int MAX_MARK = 10;

    private GradeReportValidator() {
    }

    private static boolean isValidMarks(Map<Subject, Integer> marks) {
        if (marks != null && !marks.isEmpty()) {
            for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
                if (entry.getKey() == null || entry.getValue() != null
                        || entry.getValue() < MIN_MARK || entry.getValue() > MAX_MARK) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public static boolean isValidGradeReport(Faculty faculty, double certificate, Map<Subject, Integer> marks) {
        return faculty != null && isValidCertificate(certificate)
                && isValidMarks(marks);
    }

    public static boolean isValidGradeReport(GradeReport gradeReport) {
//        if (gradeReport != null) {
//            return isValidCertificate(gradeReport.getAttestatMark())
//                    && isValidMarks(gradeReport.getMarks());
//        }
//        return false;
        return true;
    }

    private static boolean isValidCertificate(double certificate) {
        return certificate >= MIN_MARK && certificate <= MAX_MARK;
    }
}
