package com.vlasova.dao.gradereport;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.exception.CreateObjectException;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.dao.mapper.GradeReportResultSetMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class GradeReportDAOImpl implements GradeReportDAO {
    private static final Logger LOGGER = LogManager.getLogger(GradeReportDAOImpl.class);
    private static final String INSERT_GRADE_REPORT = "INSERT INTO  grade_reports(user_id, faculity_id, certificate) VALUES (?, ?, ?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO grade_repotr2subject (user_id, subject_id, mark) VALUES(?,?,?)";
    private static final String DELETE = "DELETE FROM grade_reports WHERE user_id = ?";
    private static final String DELETE_MARKS = "DELETE FROM grade_report2subject WHERE user_id = ?";
    private static final String UPDATE = "UPDATE grade_reports SET certificate = ?, isAccepted = ?, isFree = ? WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id UNION SELECT  g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id";
    private static final String FIND_BY_FACULTY = "SELECT g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id WHERE faculty_id = ? UNION SELECT  g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id WHERE faculty_id = ? ";
    private static final String FIND_BY_USER = "SELECT g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g LEFT JOIN grade_report2subject gr ON g.user_id = gr.user_id WHERE user_id = ? UNION SELECT  g.user_id, g.faculty_id, g.certificate, g.isAccepted, g.isFreePaid, gr.subject_id, gr.mark FROM grade_reports g RIGHT JOIN  grade_report2subject gr ON g.user_id = gr.user_id WHERE user_id = ? ";
    private GradeReportResultSetMapper mapper = new GradeReportResultSetMapper();
    ResultSet resultSet;

    @Override
    public void add(GradeReport gradeReport) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GRADE_REPORT)) {
            preparedStatement.setInt(1, gradeReport.getId());
            preparedStatement.setInt(2, gradeReport.getFaculty());
            preparedStatement.setDouble(3, gradeReport.getCertificate());
            preparedStatement.executeUpdate();
            addSubjectsMarks(gradeReport.getMarks(), gradeReport.getId(), connection);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    private void addSubjectsMarks(Map<Subject, Integer> marks, int gradeReportId, ProxyConnection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_SUBJECTS)) {
            for (Map.Entry<Subject, Integer> entry : marks.entrySet()) {
                preparedStatement.setInt(1, gradeReportId);
                preparedStatement.setInt(2, entry.getKey().getId());
                preparedStatement.setInt(3, entry.getValue());
                preparedStatement.executeUpdate();
            }
        }
    }

    @Override
    public void remove(GradeReport gradeReport) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS)) {
            preparedStatement.setInt(1, gradeReport.getId());
            preparedStatement.executeQuery();
            preparedStatement1.setInt(1, gradeReport.getId());
            preparedStatement1.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(GradeReport gradeReport) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setDouble(1, gradeReport.getCertificate());
            preparedStatement.setBoolean(2, gradeReport.isAccepted());
            preparedStatement.setBoolean(3, gradeReport.isFree());
            preparedStatement.setInt(4, gradeReport.getId());
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
            return mapper.createGradeReports(resultSet);
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public Set<GradeReport> findGradeReportsByFaculty(Faculty faculty) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_FACULTY)) {
            preparedStatement.setInt(1, faculty.getId());
            preparedStatement.setInt(2, faculty.getId());
            resultSet = preparedStatement.executeQuery();
            return mapper.createGradeReports(resultSet);
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public GradeReport findGradeReportByUser(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_USER)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, user.getId());
            resultSet = preparedStatement.executeQuery();
            return mapper.createGradeReports(resultSet).iterator().next();
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    private void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.warn(e);
            }
        }
    }
}
