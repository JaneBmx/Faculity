package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculity;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.pool.ConnectionPool;
import com.vlasova.pool.ProxyConnection;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class FindFaculityBySubject extends AbstractFaculitySpecification implements FaculitySpecification {
    private static final String FIND = "SELECT * FROM Faculity INNER JOIN Subject USING(subject2faculity) WHERE subject_id = ?";
    private Set<Subject> subjects;

    public FindFaculityBySubject(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    @Override
    public Set<Faculity> query() {
        faculities = new HashSet<>();
        try (ProxyConnection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND);) {
            Set<Integer> ids = getSubjectsId();
            int id = 0;
            for (Integer in : ids) {
                id = in;
                if (statement != null) {
                    statement.setInt(1, id);
                    resultSet = statement.executeQuery();
                }
                while (resultSet.next()) {
                    faculities.add(createFaculity());
                }
            }
        } catch (SQLException e) {
        } finally {
            closeResultSet();
        }
        return faculities;
    }

    private Set<Integer> getSubjectsId() {
        Set<Integer> subjectsId = new HashSet<>();
        for (Subject sub : subjects) {
            subjectsId.add(sub.getId());
        }
        return subjectsId;
    }
}
