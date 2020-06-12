package com.vlasova.util.validation;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Privilege;

import java.util.Map;

public class GradeReportValidator {
    private static final int MIN_MARK = 1;
    private static final int MAX_MARK = 10;
    private static final int MIN_ID = 0;

    public boolean isValidGradeReport(GradeReport gradeReport) {
        if (gradeReport != null) {
            return isValidGradeReport(gradeReport.getFaculty(), gradeReport.getPrivilege()
                    , gradeReport.getAttestatMark(), gradeReport.getMarks());
        }
        return false;
    }

    public boolean isValidGradeReport(Faculty faculty, Privilege privilege,
                                      double midMark, Map<Subject, Integer> marks) {
        return isValidGradeReportFaculty(faculty) && isValidPrivilege(privilege)
                && isValidMidMark(midMark) && isValidMarks(marks);
    }

    private boolean isValidGradeReportFaculty(Faculty faculty) {
        return faculty != null && faculty.getId() != MIN_ID;
    }

    private boolean isValidPrivilege(Privilege privilege) {
        return privilege != null;
    }

    private boolean isValidMidMark(double midMark) {
        return midMark >= MIN_MARK && midMark <= MAX_MARK;
    }

    private boolean isValidMarks(Map<Subject, Integer> marks) {
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
}