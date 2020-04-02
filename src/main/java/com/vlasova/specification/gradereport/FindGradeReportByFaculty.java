package com.vlasova.specification.gradereport;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class FindGradeReportByFaculty extends AbstractGradeReportSpecification implements GradeReportSpecification {
    /*
     *Tested 02.03.20
     */
    private static final String FIND_GRADE_REPORT =
            "SELECT g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark " +
                    "FROM grade_reports g LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id WHERE faculty_id = ? " +
                    "UNION " +
                    "SELECT  g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark " +
                    "FROM grade_reports g RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id WHERE faculty_id = ? ";
    private Faculty faculty;

    public FindGradeReportByFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public Set<GradeReport> query() throws QueryException {
        gradeReportMap = new HashMap<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_GRADE_REPORT)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, faculty.getId());
                preparedStatement.setInt(2, faculty.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    gradeReportMap.putAll(createGradeReport());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        gradeReportMap.remove(NON_EXIST_INDEX);
        return new HashSet<>(gradeReportMap.values());
    }
}
