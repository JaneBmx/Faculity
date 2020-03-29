package com.vlasova.specification;

import com.vlasova.exception.specification.QueryException;

import java.util.Set;

public interface Specification<T> {
    Set<T> query()throws QueryException;
}
