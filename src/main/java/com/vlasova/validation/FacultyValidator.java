package com.vlasova.validation;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

public class FacultyValidator {
    private static final int MIN = 0;
    public static boolean isValidFaculty(String name, int free, int paid, Subject... subjects) {
        return name != null && !name.isEmpty() && free >= MIN && paid >= MIN
                && free + paid > MIN && isValidSubjects(subjects);
    }

    public static boolean isValidSubjects(Subject... subjects) {
        for (Subject s : subjects) {
            if (s == null) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidFaculty(Faculty faculty) {
        return isValidFaculty(faculty.getName(), faculty.getFreeAcceptPlan(),
                faculty.getPaidAcceptPlan(), faculty.getSubjects()
                        .toArray(new Subject[faculty.getSubjects().size()]));
    }
}
