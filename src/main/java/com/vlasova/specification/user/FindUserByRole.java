package com.vlasova.specification.user;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindUserByRole extends AbstractUserSpecification implements UserSpecification {
    private static final String FIND = "SELECT * FROM Users WHERE user_id = ?";
    private Role role;

    public FindUserByRole(Role role) {
        this.role = role;
    }

    @Override
    public Set<User> query() {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, role.getRoleId());
                resultSet = preparedStatement.executeQuery();
            }
            while (resultSet.next()) {
                users.add(createUser());
            }
        } catch (SQLException e) {
            //TODO throw own exception
        } finally {
            closeResultSet();
        }
        return users;
    }
}
