package com.vlasova.dao.gradereport;

import com.vlasova.dao.AbstractDAO;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.CreateObjectException;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.dao.mapper.GradeReportResultSetMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GradeReportDAOImpl extends AbstractDAO implements GradeReportDAO {
    private static final Logger LOGGER = LogManager.getLogger(GradeReportDAOImpl.class);
    private static final String INSERT_GRADE_REPORT = "INSERT INTO  grade_reports(user_id, faculty_id, is_accepted, is_free_paid, privilege_id, attestat_mark, average_mark) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO grade_report2subject (user_id, subject_id, mark) VALUES(?,?,?)";
    private static final String DELETE_GRADE_REPORT = "DELETE FROM grade_reports WHERE user_id = ?";
    private static final String DELETE_MARKS = "DELETE FROM grade_report2subject WHERE user_id = ?";
    private static final String UPDATE = "UPDATE grade_reports " +
            "SET faculty_id = ?, is_accepted = ?, is_free_paid = ?, privilege_id = ?, attestat_mark = ?, average_mark = ? " +
            "WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT g.user_id, g.faculty_id, g.is_accepted, g.is_free_paid, g.privilege_id, g.attestat_mark, g.average_mark, " +
            "gr.subject_id, gr.mark FROM grade_reports g " +
            "LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id " +
            "UNION SELECT  g.user_id, g.faculty_id, g.is_accepted, g.is_free_paid, g.privilege_id, g.attestat_mark, g.average_mark, " +
            "gr.subject_id, gr.mark FROM grade_reports g " +
            "RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id";
      private static final String FIND_BY_USER =
            "SELECT  g.user_id, g.faculty_id, g.is_accepted, g.is_free_paid, g.privilege_id, g.attestat_mark, g.average_mark, " +
                    "gr.subject_id, gr.mark FROM grade_reports g " +
                    "LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id WHERE g.user_id = ? " +
                    "UNION SELECT   g.user_id, g.faculty_id, g.is_accepted, g.is_free_paid, g.privilege_id, g.attestat_mark, g.average_mark, " +
                    "gr.subject_id, gr.mark FROM grade_reports g RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id WHERE gr.user_id = ? ";
    private static final String ENROLL_GRADES = "UPDATE grade_reports SET is_accepted = ?, is_free_paid = ? WHERE user_id = ?";
    private static final String UN_ENROLL_GRADES = "UPDATE grade_reports SET is_accepted = 0, is_free_paid = 0;";

    private final GradeReportResultSetMapper mapper = new GradeReportResultSetMapper();

    @Override
    public void add(GradeReport gradeReport) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_REPORT)) {
            preparedStatement.setInt(1, gradeReport.getId());
            preparedStatement.setInt(2, gradeReport.getFaculty().getId());
            preparedStatement.setBoolean(3, gradeReport.isAccepted());
            preparedStatement.setBoolean(4, gradeReport.isFree());
            preparedStatement.setInt(5, gradeReport.getPrivilege().getId());
            preparedStatement.setDouble(6, gradeReport.getAttestatMark());
            preparedStatement.setDouble(7, gradeReport.getAverageMark());
            preparedStatement.executeUpdate();
            addSubjectsMarks(gradeReport.getMarks(), gradeReport.getId(), connection);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    private void addSubjectsMarks(Map<Subject, Integer> marks, int gradeReportId, ProxyConnection connection) throws SQLException {
        connection.setAutoCommit(false);
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECTS);
        for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
            preparedStatement.setInt(1, gradeReportId);
            preparedStatement.setInt(2, entry.getKey().getId());
            preparedStatement.setInt(3, entry.getValue());
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();
        preparedStatement.close();
        connection.setAutoCommit(true);
    }

    @Override
    public void remove(int gradeReportId) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_GRADE_REPORT);) {
            preparedStatement1.setInt(1, gradeReportId);
            preparedStatement1.executeUpdate();
            preparedStatement.setInt(1, gradeReportId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(GradeReport gradeReport) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, gradeReport.getFaculty().getId());
            preparedStatement.setBoolean(2, gradeReport.isAccepted());
            preparedStatement.setBoolean(3, gradeReport.isFree());
            preparedStatement.setInt(4, gradeReport.getPrivilege().getId());
            preparedStatement.setDouble(5, gradeReport.getAttestatMark());
            preparedStatement.setDouble(6, gradeReport.getAverageMark());
            preparedStatement.setInt(7, gradeReport.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Set<GradeReport> findAllGradeReports() throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(FIND_ALL);
            Set<GradeReport> gradeReports = null;
            if (resultSet.next()) {
                gradeReports = mapper.map(resultSet);
            }
            return gradeReports;
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public GradeReport findGradeReportByUserId(int id) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, id);
            resultSet = preparedStatement.executeQuery();
            Set<GradeReport> gradeReports = null;
            if (resultSet.next()) {
                gradeReports = mapper.map(resultSet);
            }
            return gradeReports == null
                    ? null
                    : gradeReports.iterator().next();
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    public void enroll(List<GradeReport> gradeReports) throws DAOException {
        try {
            ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
            connection.setAutoCommit(false);
            PreparedStatement statement = connection.prepareStatement(ENROLL_GRADES);
            for (GradeReport g : gradeReports) {
                statement.setBoolean(1, g.isAccepted());
                statement.setBoolean(2, g.isFree());
                statement.setInt(3, g.getId());
                statement.addBatch();
            }
            statement.executeBatch();
            connection.commit();
            connection.close();
            statement.close();
        } catch (SQLException e) {
            LOGGER.warn("Can't enroll gradeReports.", e);
            throw new DAOException(e);
        }
    }

    public void unEnroll() throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(UN_ENROLL_GRADES);
        } catch (SQLException e) {
            LOGGER.warn("Can't reset gradeReport status", e);
            throw new DAOException(e);
        }
    }
}