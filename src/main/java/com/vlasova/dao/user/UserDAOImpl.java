package com.vlasova.dao.user;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.mapper.dao.UserResultSetMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


public class UserDAOImpl implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String CREATE = "INSERT INTO users (user_role_id, user_name, user_surname, user_email, user_login, user_password, user_privilege) VALUES(?,?,?,?,?,?,?)";
    private static final String DELETE = "DELETE FROM users WHERE user_id = ?";
    private static final String UPDATE = "UPDATE users SET user_role_id = ?, user_name = ?, user_surname = ?, user_email = ?, user_password = ?, user_privilege =?";
    private static final String FIND_BY_LOG_AND_EMAIL = "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege FROM users WHERE login = ? AND  password = ?";
    private static final String FIND_BY_LOGIN_AND_EMAIL = "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege FROM users WHERE login = ? AND  password = ?";
    private static final String FIND_BY_ID = "SELECT user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege FROM users WHERE user_id = ?";
    private static final String FIND_ALL = "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege FROM users ";
    private static final String FIND_BY_ROLE = "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege FROM users WHERE user_role = ?";
    private UserResultSetMapper mapper = new UserResultSetMapper();
    private ResultSet resultSet;
    private Set<User> users;

    @Override
    public void add(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, user.getRole().getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getLogin());
                preparedStatement.setString(6, user.getPassword());
                preparedStatement.setInt(7, user.getPrivilege().getId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void remove(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public void update(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE)) {
            preparedStatement.setInt(1, user.getRole().getId());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getPassword());
            preparedStatement.setInt(6, user.getPrivilege().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    @Override
    public boolean existsByEmailAndLogin(String email, String login) throws DAOException {
        User user;
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN_AND_EMAIL)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, login);
                resultSet = preparedStatement.executeQuery();
                user = mapper.map(resultSet);
                return  user!=null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
        return false;
    }

    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_LOG_AND_EMAIL)) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            return mapper.map(resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public User findUserById(int id) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            return mapper.map(resultSet);
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public Set<User> findAllUsers() throws DAOException {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            if (statement != null) {
                resultSet = statement.executeQuery(FIND_ALL);
                while (resultSet.next()) {
                    users.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
        return users;
    }

    @Override
    public Set<User> findUsersByRole(Role role) throws DAOException {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ROLE)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, role.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    users.add(mapper.map(resultSet));
                }
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
        return users;
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
