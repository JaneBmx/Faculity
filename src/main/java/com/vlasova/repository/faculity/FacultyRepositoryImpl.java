package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.faculity.FacultySpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FacultyRepositoryImpl implements FacultyRepository {
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

    @Override
    public void add(Faculty faculty) throws RepositoryException {
        if (faculty != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FACULTY);
                 PreparedStatement preparedStatement1 = connection.prepareStatement(INSERT_SUBJECTS)) {
                if (preparedStatement != null) {
                    preparedStatement.setString(1, faculty.getName());
                    preparedStatement.setInt(2, faculty.getFreeAcceptPlan());
                    preparedStatement.setInt(3, faculty.getPaidAcceptPlan());
                    preparedStatement.executeUpdate();
                }
                if (preparedStatement1 != null) {
                    for (Subject sb : faculty.getSubjects()) {
                        preparedStatement1.setInt(1, faculty.getId());
                        preparedStatement1.setInt(2, sb.getId());
                        preparedStatement1.executeUpdate();
                    }
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
             PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_MARKS)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            if (preparedStatement1 != null) {
                preparedStatement1.setInt(1, id);
            }
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

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

    @Override
    public Set<Faculty> query(FacultySpecification specification) throws RepositoryException {
        if(specification!=null) {
            try {
                return specification.query();
            } catch (QueryException e) {
                throw new RepositoryException(e);
            }
        }
        return new HashSet<>();
    }
}
