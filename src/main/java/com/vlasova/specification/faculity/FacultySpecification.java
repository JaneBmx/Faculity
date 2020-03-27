package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.specification.QueryException;

import java.util.Set;

public interface FacultySpecification {
    Set<Faculty> query() throws QueryException;
}
