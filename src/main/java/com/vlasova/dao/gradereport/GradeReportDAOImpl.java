package com.vlasova.dao.gradereport;

import com.vlasova.dao.DAO;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GradeReportDAOImpl implements DAO<GradeReport> {
    /*
     *Tested 02.03.20
     */
    private static final String INSERT_GRADE_REPORT = "INSERT INTO  grade_reports(user_id, faculity_id, certificate) VALUES (?, ?, ?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO grade_repotr2subject (user_id, subject_id, mark) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM grade_reports WHERE user_id = ?";
    private static final String DELETE_MARKS = "DELETE FROM grade_report2subject WHERE user_id = ?";
    private static final String UPDATE = "UPDATE grade_reports SET certificate = ?, isAccepted = ?, isFree = ? WHERE user_id = ?";

    private static class GradeReportRepositoryHolder {
        private static final GradeReportDAOImpl INSTANCE = new GradeReportDAOImpl();
    }

    public static GradeReportDAOImpl getInstance() {
        return GradeReportRepositoryHolder.INSTANCE;
    }

    private GradeReportDAOImpl() {
    }

    @Override
    public void add(GradeReport gradeReport) throws DAOException {
        if (gradeReport != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_REPORT)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, gradeReport.getId());
                    preparedStatement.setInt(2, gradeReport.getFaculty());
                    preparedStatement.setDouble(3, gradeReport.getCertificate());
                    preparedStatement.executeUpdate();
                    addSubjectsMarks(gradeReport.getMarks(), gradeReport.getId(), connection);
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    /*
     *Paste all marks in 'grade_report2subject'
     */
    private void addSubjectsMarks(Map<Subject, Integer> marks, int gradeReportId, ProxyConnection connection) throws SQLException {
        if (marks != null && !marks.isEmpty()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECTS)) {
                if (preparedStatement != null) {
                    for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
                        preparedStatement.setInt(1, gradeReportId);
                        preparedStatement.setInt(2, entry.getKey().getId());
                        preparedStatement.setInt(3, entry.getValue());
                        preparedStatement.executeUpdate();
                    }
                }
            }
        }
    }

    /*
     * Remove 'grade_report' by id
     * remove marks from 'grade_report2subject'
     */
    @Override
    public void remove(GradeReport gradeReport) throws DAOException {
        if (gradeReport != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, gradeReport.getId());
                    preparedStatement.executeQuery();
                    preparedStatement1.setInt(1, gradeReport.getId());
                    preparedStatement1.executeUpdate();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    /*
     *Modify gradeReport
     * ('isAccept' for approve enrollee request, or not)
     * ('isFree' fot set paid aducation or not )
     */
    @Override
    public void update(GradeReport gradeReport) throws DAOException {
        if (gradeReport != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
                if (preparedStatement != null) {
                    preparedStatement.setDouble(1, gradeReport.getCertificate());
                    preparedStatement.setBoolean(2, gradeReport.isAccepted());
                    preparedStatement.setBoolean(3, gradeReport.isFree());
                    preparedStatement.setInt(4, gradeReport.getId());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new DAOException(e);
            }
        }
    }

    /*
     *Accepts any 'GradeReport' specification
     */
    @Override
    public Set<GradeReport> query(Specification<GradeReport> specification) throws DAOException {
        if (specification != null) {
            try {
                return specification.query();
            } catch (QueryException e) {
                throw new DAOException(e);
            }
        }
        return new HashSet<>();
    }
}
