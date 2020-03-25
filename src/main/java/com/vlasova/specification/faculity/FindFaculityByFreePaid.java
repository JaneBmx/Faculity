package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculity;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 * All faculities, that have free aducation
 */
public class FindFaculityByFreePaid extends AbstractFaculitySpecification implements FaculitySpecification {
    private static final String FIND = "SELECT * FROM Faculity WHERE free_aducation_plan IS NOT NULL";

    public FindFaculityByFreePaid() {
    }

    @Override
    public Set<Faculity> query() {
        faculities = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND)) {
            if (statement != null) {
                resultSet = statement.executeQuery();
            }
            while (resultSet.next()) {
                faculities.add(createFaculity());
            }
        } catch (SQLException e) {

        } finally {
            closeResultSet();
        }
        return faculities;
    }
}
