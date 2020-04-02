package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.Specification;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
    /*
     *Tested 02.03.20
     */
    private static final String INSERT = "INSERT INTO users (user_role_id, user_name, user_surname, user_email, user_login, user_password, user_privilege) VALUES(?,?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM users WHERE user_id = ?";
    private static final String UPDATE = "UPDATE users SET user_role_id = ?, user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_privilege =?";

    private static class UserRepositoryHolder {
        private static final UserRepositoryImpl INSTANCE = new UserRepositoryImpl();
    }

    public static UserRepositoryImpl getInstance() {
        return UserRepositoryImpl.UserRepositoryHolder.INSTANCE;
    }

    private UserRepositoryImpl() {
    }

    /*
     *Add user in 'users'
     */
    @Override
    public void add(User user) throws RepositoryException {
        if (user != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT)) {
                if (preparedStatement != null) {
                    preparedStatement.setInt(1, user.getRole().getRoleId());
                    preparedStatement.setString(2, user.getName());
                    preparedStatement.setString(3, user.getSurname());
                    preparedStatement.setString(4, user.getEmail());
                    preparedStatement.setString(5, user.getLogin());
                    preparedStatement.setString(6, user.getPassword());
                    preparedStatement.setInt(7, user.getPrivilege().getId());
                    preparedStatement.executeUpdate();
                }
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    /*
     *Remove user from 'users' by user.id
     *Doesn't remove gradeReport, impossible to access it without 'user.id'
     *Should use GradeReportRepository.remove for deleting 'user.gradeReport'
     */
    @Override
    public void remove(User user) throws RepositoryException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE);) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException(e);
        }
    }

    /*
     *Update user at 'users' by user.id
     *Doesn't update gradeReport, impossible to access it without 'user.id'
     *Should use GradeReportRepository.update for deleting 'user.gradeReport'
     */
    @Override
    public void update(User user) throws RepositoryException {
        if (user != null) {
            try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);) {
                preparedStatement.setInt(1, user.getRole().getRoleId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getPassword());
                preparedStatement.setInt(6, user.getPrivilege().getId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RepositoryException(e);
            }
        }
    }

    /*
     *Accepts any 'User' specification
     */
    @Override
    public Set<User> query(Specification<User> specification) throws RepositoryException {
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
