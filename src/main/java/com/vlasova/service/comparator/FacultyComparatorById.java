package com.vlasova.service.comparator;

import com.vlasova.entity.faculity.Faculty;

import java.util.Comparator;

public class FacultyComparatorById implements Comparator<Faculty> {
    @Override
    public int compare(Faculty o1, Faculty o2) {
        Comparator<Faculty> comparator = Comparator.comparing(Faculty::getId);
        return comparator.compare(o1, o2);
    }
}
