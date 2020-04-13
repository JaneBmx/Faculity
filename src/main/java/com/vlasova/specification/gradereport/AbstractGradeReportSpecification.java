package com.vlasova.specification.gradereport;

import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractGradeReportSpecification {
    private static Logger logger = LogManager.getLogger(AbstractGradeReportSpecification.class);
    protected static final int NON_EXIST_INDEX = -1;
    private static final String USER_ID = "user_id";
    private static final String FACULTY_ID = "faculty_id";
    private static final String CERTIFICATE = "certificate";
    private static final String IS_ACCEPTED = "isAccepted";
    private static final String IS_FREE_PAID = "isFreePaid";
    private static final String SUBJECT_ID = "subject_id";
    private static final String MARK = "mark";

    protected ResultSet resultSet;
    /*
     *Integer as 'user.id' for better searching
     */
    protected Map<Integer, GradeReport> gradeReportMap;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }

    protected Map<Integer, GradeReport> createGradeReport() {
        GradeReport gradeReport = null;
        int userId = -1;
        if (resultSet != null) {
            try {
                userId = resultSet.getInt(USER_ID);
                if (gradeReportMap.containsKey(userId)) {
                    gradeReport = gradeReportMap.get(userId);
                } else {
                    gradeReport = new GradeReport();
                    gradeReport.setId(userId);
                    gradeReport.setFacultyId(resultSet.getInt(FACULTY_ID));
                    gradeReport.setCertificate(resultSet.getDouble(CERTIFICATE));
                    gradeReport.setAccepted(resultSet.getBoolean(IS_ACCEPTED));
                    gradeReport.setFree(resultSet.getBoolean(IS_FREE_PAID));
                    gradeReport.setMarks(new HashMap<>());
                }
                gradeReport.getMarks().put(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)),
                        resultSet.getInt(MARK));
            } catch (SQLException e) {
                logger.warn("Fail to create GradeReport with id =" + userId, e);
            }
        }
        Map<Integer, GradeReport> map = new HashMap<>();
        map.put(userId, gradeReport);
        return map;
    }
}
