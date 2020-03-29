package com.vlasova.entity.faculity;

import java.util.HashSet;
import java.util.Set;

public enum Faculties {
    FACULTIES;
    private Set<Faculty> faculties;

    private Faculties() {
        faculties = new HashSet<>();
    }

    public void addFaculty(Faculty faculty) {
        if (faculty != null) {
            faculties.add(faculty);
        }
    }

    public void remove(Faculty faculty) {
        faculties.remove(faculty);
    }

    public boolean isExist(Faculty faculty) {
        return faculties.contains(faculty);
    }
}
