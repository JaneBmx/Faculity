package com.vlasova.dao.mapper;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.exception.dao.CreateObjectException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FacultyResultSetMapper {
    private static final Logger LOGGER = LogManager.getLogger(FacultyResultSetMapper.class);
    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String FREE_ACCEPT_PLAN = "free_accept_plan";
    private static final String PAID_ACCEPT_PLAN = "paid_accept_plan";
    private static final String SUBJECT_ID = "subject_id";

    public Set<Faculty> map(ResultSet resultSet) throws CreateObjectException {
        Map<Integer, Faculty> facultyMap = new HashMap<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(FACULTY_ID);
                Faculty faculty;
                if (facultyMap.containsKey(id)) {
                    faculty = facultyMap.get(id);
                } else {
                    faculty = new Faculty();
                    facultyMap.put(id, faculty);
                    faculty.setId(id);
                    faculty.setName(resultSet.getString(FACULTY_NAME));
                    faculty.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
                    faculty.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));
                    faculty.setSubjects(new HashSet<>());
                }
                faculty.getSubjects().add(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)));
            }
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new CreateObjectException(e);
        }
        return new HashSet<>(facultyMap.values());
    }

    public Faculty mapOne(ResultSet resultSet) throws CreateObjectException {
        Faculty faculty = new Faculty();
        try {
            faculty.setId(resultSet.getInt(FACULTY_ID));
            faculty.setName(resultSet.getString(FACULTY_NAME));
            faculty.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
            faculty.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));
        } catch (SQLException e) {
            LOGGER.warn(e);
            throw new CreateObjectException(e);
        }
        return faculty;
    }
}
