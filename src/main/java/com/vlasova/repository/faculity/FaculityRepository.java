package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.repository.Repository;
import com.vlasova.specification.faculity.FacultySpecification;

import java.util.Set;

public interface FaculityRepository extends Repository {
    void add(Faculty user);

    void remove(int id) throws RepositoryException;

    void update(Faculty user) throws RepositoryException;

    Set<Faculty> query(FacultySpecification specification) throws QueryException, RepositoryException;
}
