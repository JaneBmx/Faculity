package com.vlasova.repository;

import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.specification.Specification;

import java.util.Set;

public interface Dao<T> {
    void add(T t) throws RepositoryException;

    void remove(T t) throws RepositoryException;

    void update(T t) throws RepositoryException;

    Set<T> query(Specification<T> specification) throws RepositoryException;
}
