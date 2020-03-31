package com.vlasova.specification.gradereport;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class FindAllGradeReports extends AbstractGradeReportSpecification implements GradeReportSpecification {
    private static final String FIND_ALL_GRADE_REPORTS = "SELECT * FROM grade_reports";

    @Override
    public Set<GradeReport> query() throws QueryException {
        gradeReports = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            if (statement != null) {
                resultSet = statement.executeQuery(FIND_ALL_GRADE_REPORTS);
                while (resultSet.next()) {
                    gradeReports.add(createGradeReport());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return null;
    }
}
