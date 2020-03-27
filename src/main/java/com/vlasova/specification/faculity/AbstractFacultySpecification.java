package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public abstract class AbstractFacultySpecification {
    private static final String ID = "faculty_id";
    private static final String NAME = "faculty_name";
    private static final String FREE_ACCEPT_PLAN = "free_accept_plan";
    private static final String PAID_ACCEPT_PLAN = "paid_accept_plan";

    private static final String SUBJECT_ID = "subject_id";
    private static final String PRIORITY = "priority";

    protected ResultSet resultSet;
    protected Set<Faculty> faculties;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    protected Faculty createFaculty() {
        Faculty faculty = null;
        Map<Subject, Integer> subjects;

        if (resultSet != null) {
            faculty = new Faculty();
            subjects = new HashMap<>();
            try {
                faculty.setId(resultSet.getInt(ID));
                faculty.setName(resultSet.getString(NAME));
                faculty.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
                faculty.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));

                subjects.put(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)), resultSet.getInt(PRIORITY));
                faculty.setSubjects(subjects);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return faculty;
    }
}