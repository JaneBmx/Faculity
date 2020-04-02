package com.vlasova.specification.faculity;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public abstract class AbstractFacultySpecification {
    private static Logger logger = LogManager.getLogger(AbstractFacultySpecification.class);
    protected static final int NON_EXIST_INDEX = -1;
    private static final String FACULTY_ID = "faculty_id";
    private static final String FACULTY_NAME = "faculty_name";
    private static final String FREE_ACCEPT_PLAN = "free_accept_plan";
    private static final String PAID_ACCEPT_PLAN = "paid_accept_plan";
    private static final String SUBJECT_ID = "subject_id";

    protected ResultSet resultSet;
    /*
     *Integer as 'faculty.id' for better searching
     */
    protected Map<Integer, Faculty> faculties;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }

    protected Map<Integer, Faculty> createFaculty() {
        Faculty faculty = null;
        int facultyId = -1;
        if (resultSet != null) {
            try {
                facultyId = resultSet.getInt(FACULTY_ID);
                if (faculties.containsKey(facultyId)) {
                    faculty = faculties.get(facultyId);
                } else {
                    faculty = new Faculty();
                    faculty.setId(facultyId);
                    faculty.setName(resultSet.getString(FACULTY_NAME));
                    faculty.setFreeAcceptPlan(resultSet.getInt(FREE_ACCEPT_PLAN));
                    faculty.setPaidAcceptPlan(resultSet.getInt(PAID_ACCEPT_PLAN));
                    faculty.setSubjects(new HashSet<>());
                }
                faculty.getSubjects().add(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)));
            } catch (SQLException e) {
                logger.warn("Fail to create faculty with id = " + facultyId, e);
            }
        }
        Map<Integer, Faculty> map = new HashMap<>();
        map.put(facultyId, faculty);
        return map;
    }
}