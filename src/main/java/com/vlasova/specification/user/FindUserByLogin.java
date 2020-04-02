package com.vlasova.specification.user;

import com.vlasova.entity.user.User;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/*
 *For verify the uniq of the user while creating new one
 */
public class FindUserByLogin extends AbstractUserSpecification implements UserSpecification {
    /*
     *02.04.20
     */
    private static final String FIND =
            "SELECT user_id, user_role, user_name, user_surname, user_email, user_login, user_password, user_privilege " +
                    "FROM users WHERE user_login = ?";
    private String userLogin;

    public FindUserByLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    @Override
    public Set<User> query() throws QueryException {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            if (preparedStatement != null) {
                preparedStatement.setString(1, userLogin);
                resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    users.add(createUser());
                }
            }
        } catch (SQLException e) {
            throw new QueryException(e);
        } finally {
            closeResultSet();
        }
        return users;
    }
}
