package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.repository.Repository;
import com.vlasova.specification.faculity.FacultySpecification;

import java.util.Set;

public interface FaculityRepository extends Repository {
    void add(Faculty user);

    void remove(int id);

    void update(Faculty user);

    Set<Faculty> query(FacultySpecification specification);
}
