package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.Specification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FacultyRepositoryImpl implements FacultyRepository {
    /*
     *Tested 02.03.20
     */
    private static final String INSERT_FACULTY = "INSERT INTO faculties(faculty_name, free_accept_plan, paid_accept_plan) VALUES(?,?,?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO subject2faculty(faculty_id, subject_id) VALUES (?,?)";
    private static final String DELETE = "DELETE FROM faculties WHERE faculty_id = ?";
    private static final String DELETE_MARKS = "DELETE FROM subject2faculty WHERE faculty_id = ?";
    private static final String UPDATE = "UPDATE faculty SET faculty_name = ?, free_accept_plan = ?, paid_accept_plan = ? WHERE faculty_id = ?";

    private static class FacultyRepositoryHolder {
        private static final FacultyRepositoryImpl INSTANCE = new FacultyRepositoryImpl();
    }

    public static FacultyRepositoryImpl getInstance() {
        return FacultyRepositoryHolder.INSTANCE;
    }

    private FacultyRepositoryImpl() {
    }

    /*
     *Add Faculty in 'faculties'
     *Add Faculty.subjects in 'subject2faculty'
     */
    @Override
    public void add(Faculty faculty) throws RepositoryException {
        if (faculty != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatementFaculty = connection.prepareStatement(INSERT_FACULTY);
                 PreparedStatement preparedStatementSubjects = connection.prepareStatement(INSERT_SUBJECTS)) {
                if (preparedStatementFaculty != null) {
                    preparedStatementFaculty.setString(1, faculty.getName());
                    preparedStatementFaculty.setInt(2, faculty.getFreeAcceptPlan());
                    preparedStatementFaculty.setInt(3, faculty.getPaidAcceptPlan());
                    preparedStatementFaculty.executeUpdate();
                }
                if (preparedStatementSubjects != null) {
                    for (Subject sb : faculty.getSubjects()) {
                        preparedStatementSubjects.setInt(1, faculty.getId());
                        preparedStatementSubjects.setInt(2, sb.getId());
                        preparedStatementSubjects.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    /*
     *Remove faculty by faculty.id from 'faculties'
     *Remove subjects by faculty.is from 'subject2faculty'
     */
    @Override
    public void remove(Faculty faculty) throws RepositoryException {
        if (faculty != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, faculty.getId());
                    preparedStatement.executeUpdate();
                }
                if (preparedStatement1 != null) {
                    preparedStatement1.setInt(1, faculty.getId());
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    /*
     *Update faculty by faculty.id from 'faculties'
     */
    @Override
    public void update(Faculty faculty) throws RepositoryException {
        if (faculty != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
                preparedStatement.setString(1, faculty.getName());
                preparedStatement.setInt(2, faculty.getFreeAcceptPlan());
                preparedStatement.setInt(3, faculty.getPaidAcceptPlan());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    /*
     *Accepts any 'Faculty' specification
     */
    @Override
    public Set<Faculty> query(Specification<Faculty> specification) throws RepositoryException {
        if (specification != null) {
            try {
                return specification.query();
            } catch (QueryException e) {
                throw new RepositoryException(e);
            }
        }
        return new HashSet<>();
    }
}
