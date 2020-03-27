package com.vlasova.specification.gradereport;

import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindGradeReportBySubjects extends AbstractGradeReportSpecification implements GradeReportSpecification {
    private static final String FIND = "SELECT * FROM faculties WHERE ";
    private Set<Subject> subjects;

    public FindGradeReportBySubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public Set<GradeReport> query() throws QueryException {
        gradeReports = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND);) {
            Set<Integer> ids = getSubjectsId();
            int id = 0;
            for (Integer in : ids) {
                id = in;
                if (statement != null) {
                    statement.setInt(1, id);
                    resultSet = statement.executeQuery();
                    while (resultSet.next()) {
                        gradeReports.add(createGradeReport());
                    }
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return gradeReports;
    }

    private Set<Integer> getSubjectsId() {
        Set<Integer> subjectsId = new HashSet<>();
        for (Subject sub : subjects) {
            subjectsId.add(sub.getId());
        }
        return subjectsId;
    }
}
