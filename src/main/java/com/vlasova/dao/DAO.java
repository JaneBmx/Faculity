package com.vlasova.dao;

import com.vlasova.exception.dao.DAOException;
import com.vlasova.specification.Specification;

import java.util.Set;

public interface DAO<T> {
    void add(T t) throws DAOException;

    void remove(T t) throws DAOException;

    void update(T t) throws DAOException;

    Set<T> query(Specification<T> specification) throws DAOException;
}
