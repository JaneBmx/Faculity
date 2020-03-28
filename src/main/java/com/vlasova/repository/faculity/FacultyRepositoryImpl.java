package com.vlasova.repository.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.faculity.FacultySpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class FacultyRepositoryImpl implements FaculityRepository {
    private static final String INSERT_FACULTY = "INSERT INTO faculties(faculty_name, free_accept_plan, paid_accept_plan) VALUES(?,?,?)";
    private static final String INSERT_SUBJECTS = "INSERT INTO subject2faculty(faculty_id, subject_id, priority) VALUES (?,?,?)";

    private static final String DELETE = "DELETE FROM faculties WHERE faculty_id = ?";
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
    public void add(Faculty faculty) {
        
    }

    @Override
    public void remove(int id) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    @Override
    public void update(Faculty faculty) throws RepositoryException {
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

    @Override
    public Set<Faculty> query(FacultySpecification specification) throws RepositoryException {
        try {
            return specification.query();
        } catch (QueryException e) {
            throw new RepositoryException(e);
        }
    }
}
