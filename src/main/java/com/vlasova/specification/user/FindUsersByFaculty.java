package com.vlasova.specification.user;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.User;
import com.vlasova.exception.specification.QueryException;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindUsersByFaculty extends AbstractUserSpecification implements UserSpecification {
    private static final String FIND = "SELECT *FROM users INNER JOIN ...?";
    private Faculty faculty;

    public FindUsersByFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public Set<User> query() throws QueryException {
        users = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND)) {
            if (preparedStatement != null) {
                preparedStatement.setInt(1, faculty.getId());
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