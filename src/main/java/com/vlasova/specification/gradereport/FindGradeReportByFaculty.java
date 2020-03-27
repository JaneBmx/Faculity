package com.vlasova.specification.gradereport;

import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindGradeReportByFaculty extends AbstractGradeReportSpecification implements GradeReportSpecification {
    private static final String FIND = "SELECT * FROM gradereport WHERE faculity_id = ?";
    private int id;

    public FindGradeReportByFaculty(int id) {
        this.id = id;
    }

    @Override
    public Set<GradeReport> query() throws QueryException {
        gradeReports = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    gradeReports.add(createGradeReport());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return gradeReports;
    }
}
