package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculity;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindFaculityById extends AbstractFaculitySpecification implements FaculitySpecification {
    private static final String FIND = "SELECT * FROM Faculity WHERE faculity_id = ?";
    private int faculityId;

    public FindFaculityById(int id) {
        this.faculityId = id;
    }

    @Override
    public Set<Faculity> query() {
        faculities = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND);) {
            if (statement != null) {
                statement.setInt(1, faculityId);
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()) {
                faculities.add(createFaculity());
                //TODO smth with subjects
            }
        } catch (SQLException e) {
            //TODO own exception
        } finally {
            closeResultSet();
        }
        return faculities;
    }
}
