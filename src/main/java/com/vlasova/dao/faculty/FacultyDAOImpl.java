package com.vlasova.dao.faculty;

import com.vlasova.dao.AbstractDAO;
import com.vlasova.dao.mapper.FacultyResultSetMapper;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.dao.exception.dao.CreateObjectException;
import com.vlasova.dao.exception.dao.DAOException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Set;

public class FacultyDAOImpl extends AbstractDAO implements FacultyDAO {
    private static final Logger LOGGER = LogManager.getLogger(FacultyDAOImpl.class);
    private final FacultyResultSetMapper mapper = new FacultyResultSetMapper();

    private static final String INSERT_FACULTY     = "INSERT INTO faculties(faculty_name, free_accept_plan, paid_accept_plan) " +
                                                     "VALUES(?,?,?)";
    private static final String INSERT_SUBJECTS    = "INSERT INTO faculty2subject (faculty_id, subject_id) VALUES (?,?)";
    private static final String DELETE             = "DELETE FROM faculties WHERE faculty_id = ?";
    private static final String DELETE_MARKS       = "DELETE FROM faculty2subject WHERE faculty_id = ?";
    private static final String UPDATE             = "UPDATE faculty SET faculty_name = ?, free_accept_plan = ?, paid_accept_plan = ? " +
                                                     "WHERE faculty_id = ?";
    private static final String FIND_ALL_FACULTIES = "SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                                                     "FROM faculties f LEFT JOIN  faculty2subject sf ON f.faculty_id = sf.faculty_id " +
                                                     "UNION SELECT f.faculty_id, f.faculty_name, f.free_accept_plan, f.paid_accept_plan, sf.subject_id " +
                                                     "FROM faculties f RIGHT JOIN faculty2subject sf ON f.faculty_id = sf.faculty_id;";
    private static final String FIND_BY_ID         = "SELECT * FROM faculties WHERE faculty_id = ?";
    private static final String FIND_BY_NAME       = "SELECT * FROM faculties WHERE faculty_name = ?";
    private static final String COMMON_STAT        = "SELECT COUNT(*) AS fac_count, SUM(free_accept_plan) AS free_plan, " +
                                                     "(SUM(paid_accept_plan) +SUM(free_accept_plan)) AS all_plan, " +
                                                     "(SELECT COUNT(*) FROM grade_reports) AS enroles FROM faculties";

    @Override
    public void add(Faculty faculty) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection()) {
            connection.setAutoCommit(false);
            addFaculty(connection, faculty);
            Faculty faculty1 = findByName(connection, faculty.getName());
            if (faculty1 != null) {
                addSubjects(connection, faculty1.getId(), faculty.getSubjects());
            }
            connection.commit();
        } catch (CreateObjectException | SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    /**
     * Add faculty into database only with name, free accept plan, paid accept plan
     *
     * @param connection
     * @param faculty
     * @throws SQLException
     */
    private void addFaculty(ProxyConnection connection, Faculty faculty) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_FACULTY);
        statement.setString(1, faculty.getName());
        statement.setInt(2, faculty.getFreeAcceptPlan());
        statement.setInt(3, faculty.getPaidAcceptPlan());
        statement.executeUpdate();
        connection.commit();
        statement.close();
    }

    /**
     * Get faculty from db with id (given by db)
     *
     * @param connection
     * @param name
     * @return incomplete Faculty
     * @throws SQLException
     * @throws CreateObjectException
     * @see Faculty
     */
    private Faculty findByName(ProxyConnection connection, String name) throws SQLException, CreateObjectException {
        PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME);
        statement.setString(1, name);
        resultSet = statement.executeQuery();
        Faculty faculty = null;
        if (resultSet.next()) {
            faculty = mapper.mapOne(resultSet);
        }
        statement.close();
        return faculty;
    }

    /**
     * Add subjects to adjacent table
     *
     * @param connection
     * @param facultyId  faculty id
     * @param subjects   Set with subjects
     * @throws SQLException
     */
    private void addSubjects(ProxyConnection connection, int facultyId, Set<Subject> subjects) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(INSERT_SUBJECTS);
        for (Subject s : subjects) {
            statement.setInt(1, facultyId);
            statement.setInt(2, s.getId());
            statement.addBatch();
        }
        statement.executeBatch();
        connection.commit();
    }

    @Override
    public void remove(int facultyId) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS);
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement1.setInt(1, facultyId);
            preparedStatement1.executeUpdate();
            preparedStatement.setInt(1, facultyId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(Faculty faculty) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
            preparedStatement.setString(1, faculty.getName());
            preparedStatement.setInt(2, faculty.getFreeAcceptPlan());
            preparedStatement.setInt(3, faculty.getPaidAcceptPlan());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public Set<Faculty> findAllFaculties() throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(FIND_ALL_FACULTIES);
            return mapper.map(resultSet);
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public Faculty findFacultyById(int id) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapper.mapOne(resultSet);
            }
            return null;
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    public boolean isExistByName(String name) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_NAME)) {
            statement.setString(1, name);
            resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    public Map<String, Integer> getCommonStatistics() throws DAOException{
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            resultSet = statement.executeQuery(COMMON_STAT);
            if (resultSet.next()) {
                return mapper.mapInfo(resultSet);
            }
            return null;
        } catch (SQLException | CreateObjectException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }
}