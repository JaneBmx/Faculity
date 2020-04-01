package com.vlasova.validation;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

public class FacultyValidator {
    public static boolean isValidFaculty(String name, int free, int paid, Subject... subjects) {
        return name != null && !name.isEmpty() && free >= 0 && paid >= 0
                && free + paid > 0 && isValidSubjects(subjects);
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
