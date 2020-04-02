package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindAllFaculties extends AbstractFacultySpecification implements FacultySpecification {
    /*
     *Tested 02.04.20
     */
    private static final String FIND_ALL_FACULTIES =
            "SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                    "FROM faculties f LEFT JOIN  subject2faculty sf ON f.faculty_id = sf.faculty_id " +
                    "UNION " +
                    "SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                    "FROM faculties f RIGHT JOIN subject2faculty sf ON f.faculty_id = sf.faculty_id;";

    @Override
    public Set<Faculty> query() throws QueryException {
        faculties = new HashMap<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            if (statement != null) {
                resultSet = statement.executeQuery(FIND_ALL_FACULTIES);
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
