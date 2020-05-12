package com.vlasova.dao.user;

import com.vlasova.dao.AbstractDAO;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;
import com.vlasova.mapper.dao.UserResultSetMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

public class UserDAOImpl extends AbstractDAO implements UserDAO {
    private static final Logger LOGGER = LogManager.getLogger(UserDAOImpl.class);
    private static final String CREATE_USER = "INSERT INTO users (user_role_id, user_name, user_surname, user_email, user_login, user_password) VALUES(?,?,?,?,?,?)";
    private static final String DELETE_USER = "DELETE FROM users WHERE user_id = ?";
    private static final String UPDATE_USER_BY_ID = "UPDATE users SET user_name = ?, user_surname = ?, user_password = ? WHERE user_id = ?";
    private static final String FIND_USER_BY_LOGIN_AND_PASSWORD = "SELECT user_id, user_role_id, user_name, user_surname, user_email, user_login, user_password FROM users WHERE user_login = ? AND  user_password = ?";
    private static final String FIND_USER_BY_LOGIN_AND_EMAIL = "SELECT user_id, user_role_id, user_name, user_surname, user_email, user_login, user_password FROM users WHERE user_login = ? AND  user_email = ?";
    private static final String FIND_USER_BY_ID = "SELECT user_role_id, user_name, user_surname, user_email, user_login, user_password FROM users WHERE user_id = ?";
    private static final String FIND_ALL_USERS = "SELECT user_id, user_role_id, user_name, user_surname, user_email, user_login, user_password FROM users ";
    private static final String FIND_USERS_BY_ROLE = "SELECT user_id, user_role_id, user_name, user_surname, user_email, user_login, user_password FROM users WHERE user_role_id = ?";
    private final UserResultSetMapper mapper = new UserResultSetMapper();
    private Set<User> users;

    //updated & tested with front
    @Override
    public void add(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(CREATE_USER)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, user.getRole().getId());
                preparedStatement.setString(2, user.getName());
                preparedStatement.setString(3, user.getSurname());
                preparedStatement.setString(4, user.getEmail());
                preparedStatement.setString(5, user.getLogin());
                preparedStatement.setString(6, user.getPassword());
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
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)) {
            preparedStatement.setInt(1, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    //updated & tested with front
    @Override
    public void update(User user) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_BY_ID)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setInt(4, user.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        }
    }

    //updated & tested with front
    @Override
    public boolean existsByEmailAndLogin(String email, String login) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_EMAIL)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, login);
                resultSet = preparedStatement.executeQuery();
                User user = null;
                if (resultSet.next()) {
                    user = mapper.map(resultSet);
                }
                return user != null;
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
        return false;
    }

    //updated & tested with front
    @Override
    public User findUserByLoginAndPassword(String login, String password) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_LOGIN_AND_PASSWORD)) {
            statement.setString(1, login);
            statement.setString(2, password);
            resultSet = statement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = mapper.map(resultSet);
            }
            return user;
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
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USER_BY_ID)) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            User user = null;
            if (resultSet.next()) {
                user = mapper.map(resultSet);
            }
            return user;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public Set<User> findAllUsers() throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             Statement statement = connection.createStatement()) {
            users = new HashSet<>();
            if (statement != null) {
                resultSet = statement.executeQuery(FIND_ALL_USERS);
                while (resultSet.next()) {
                    users.add(mapper.map(resultSet));
                }
            }
            return users;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }

    @Override
    public Set<User> findUsersByRole(Role role) throws DAOException {
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERS_BY_ROLE)) {
            users = new HashSet<>();
            if (preparedStatement != null) {
                preparedStatement.setInt(1, role.getId());
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    users.add(mapper.map(resultSet));
                }
            }
            return users;
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new DAOException(e);
        } finally {
            closeResultSet();
        }
    }
}