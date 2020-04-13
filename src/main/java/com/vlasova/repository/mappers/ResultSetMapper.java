package com.vlasova.repository.mappers;

import java.sql.ResultSet;

public interface ResultSetMapper<T> {
    T map(ResultSet resultSet);
}
