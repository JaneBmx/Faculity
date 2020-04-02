package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindFacultyBySubject extends AbstractFacultySpecification implements FacultySpecification {
    private static final String FIND =
            "SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                    "FROM faculties f LEFT JOIN  subject2faculty sf ON f.faculty_id = sf.faculty_id " +
                    "WHERE sf.subject_id " +
                    "UNION " +
                    "SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                    "FROM faculties f RIGHT JOIN subject2faculty sf ON f.faculty_id = sf.faculty_id " +
                    "WHERE sf.subject_id = ?;";
    //TODO rework this query
    private Subject subject;

    public FindFacultyBySubject(Subject subject) {
        this.subject = subject;
    }

    @Override
    public Set<Faculty> query() throws QueryException {
        faculties = new HashMap<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND);) {
            if (statement != null) {
                statement.setInt(1, subject.getId());
                statement.setInt(2, subject.getId());
                resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    faculties.putAll(createFaculty());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        faculties.remove(NON_EXIST_INDEX);
        return new HashSet<>(faculties.values());
    }
}
