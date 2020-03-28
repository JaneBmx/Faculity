package com.vlasova.repository.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.repository.RepositoryException;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.specification.user.UserSpecification;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Set;

public class UserRepositoryImpl implements UserRepository {
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

    @Override
    public void add(User user) throws RepositoryException {
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
    public void update(User user) throws RepositoryException {
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

    @Override
    public Set<User> query(UserSpecification specification) throws RepositoryException {
        try {
            return specification.query();
        } catch (QueryException e) {
            throw new RepositoryException(e);
        }
    }
}
