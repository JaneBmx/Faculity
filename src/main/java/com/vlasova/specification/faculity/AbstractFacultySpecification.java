package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFacultySpecification {
    private static Logger logger = LogManager.getLogger(AbstractFacultySpecification.class);
    private static final String ID = "faculty_id";
    private static final String NAME = "faculty_name";
    private static final String FREE_ACCEPT_PLAN = "free_accept_plan";
    private static final String PAID_ACCEPT_PLAN = "paid_accept_plan";
    private static final String SUBJECT_ID = "subject_id";

    protected ResultSet resultSet;
    protected Set<Faculty> faculties;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }

    protected Faculty createFaculty() {
        Faculty faculty = null;
        Set<Subject> subjects = null;
        if (resultSet != null) {
            faculty = new Faculty();
            subjects = new HashSet<>();
            try {
                faculty.setId(resultSet.getInt(ID));
                faculty.setName(resultSet.getString(NAME));
                faculty.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
                faculty.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));

                subjects.add(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)));
                faculty.setSubjects(subjects);
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        return faculty;
    }
}