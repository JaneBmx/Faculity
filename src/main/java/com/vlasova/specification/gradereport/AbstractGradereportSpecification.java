package com.vlasova.specification.gradereport;

import com.vlasova.entity.user.GradeReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class AbstractGradeReportSpecification {
    private static Logger logger= LogManager.getLogger(AbstractGradeReportSpecification.class);
    private static final String ID = "grade_report_id";
    private static final String USER_ID = "user_id";
    private static final String CERTIFICATE = "certificate";
    private static final String IS_ACCEPTED = "is_accepted";
    private static final String IS_FREE = "is_free";

    private static final String SUBJECT_ID = "subject_id";
    private static final String MARK = "mark";
    //TODO smth with marks map

    protected ResultSet resultSet;
    protected Set<GradeReport> gradeReports;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
    }

    protected GradeReport createGradeReport() {
        GradeReport gradeReport = null;
        if (resultSet != null) {
            gradeReport = new GradeReport();
            try {
                gradeReport.setId(resultSet.getInt(ID));
                gradeReport.setId(resultSet.getInt(USER_ID));
                gradeReport.setAccepted(resultSet.getBoolean(IS_ACCEPTED));
                gradeReport.setFree(resultSet.getBoolean(IS_FREE));
                gradeReport.setCertificate(resultSet.getDouble(CERTIFICATE));
                //TODO add smth with map marks
            } catch (SQLException e) {
                logger.warn(e);
            }
        }
        return gradeReport;
    }
}
