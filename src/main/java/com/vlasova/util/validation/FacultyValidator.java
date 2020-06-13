package com.vlasova.util.validation;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

import java.util.Set;

public class FacultyValidator {
    private static final int MIN_PLAN = 0;

    public boolean isValidFaculty(String name, int free, int paid, Set<Subject> subjects) {
        return isValidName(name) && isValidFreeAcceptPlan(free)
                && isValidAcceptPlan(paid) && isValidSubjects(subjects);
    }

    public boolean isValidFaculty(Faculty faculty) {
        return faculty != null
                && isValidFaculty(faculty.getName(), faculty.getFreeAcceptPlan()
                , faculty.getPaidAcceptPlan(), faculty.getSubjects());
    }

    private boolean isValidSubjects(Set<Subject> subjects) {
        if (subjects != null && subjects.size() == 3) {
            for (Subject s : subjects) {
                if (s == null) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean isValidName(String facultyName) {
        return facultyName != null && !facultyName.isEmpty();
    }

    private boolean isValidAcceptPlan(int acceptPlan) {
        return acceptPlan > MIN_PLAN;
    }

    private boolean isValidFreeAcceptPlan(int freeAcceptPlan) {
        return freeAcceptPlan >= MIN_PLAN;
    }
}