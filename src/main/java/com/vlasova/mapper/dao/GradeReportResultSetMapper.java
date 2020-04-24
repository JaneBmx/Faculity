package com.vlasova.mapper.dao;

import com.vlasova.dao.faculty.FacultyDAOImpl;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Privilege;
import com.vlasova.exception.dao.CreateObjectException;
import com.vlasova.exception.dao.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GradeReportResultSetMapper {
    private static final Logger LOGGER = LogManager.getLogger(GradeReportResultSetMapper.class);
    private static final String USER_ID = "user_id";
    private static final String FACULTY_ID = "faculty_id";
    private static final String IS_ACCEPTED = "is_accepted";
    private static final String IS_FREE_PAID = "is_free_paid";
    private static final String PRIVILEGE = "privilege_id";
    private static final String ATTESTAT_MARK = "attestat_mark";
    private static final String AVERAGE_MARK = "average_mark";
    private static final String SUBJECT_ID = "subject_id";
    private static final String MARK = "mark";

    public Set<GradeReport> map(ResultSet resultSet) throws CreateObjectException {
        Map<Integer, GradeReport> gradeReportMap = new HashMap<>();
        try {
            while (resultSet.next()) {
                int id = resultSet.getInt(USER_ID);
                GradeReport gradeReport;
                if (gradeReportMap.containsKey(id)) {
                    gradeReport = gradeReportMap.get(id);
                } else {
                    gradeReport = new GradeReport();
                    gradeReportMap.put(id, gradeReport);
                    gradeReport.setId(id);
                    gradeReport.setFaculty(new FacultyDAOImpl().findFacultyById(resultSet.getInt(FACULTY_ID)));
                    gradeReport.setAccepted(resultSet.getBoolean(IS_ACCEPTED));
                    gradeReport.setFree(resultSet.getBoolean(IS_FREE_PAID));
                    gradeReport.setPrivilege(Privilege.getPrivilegeById(resultSet.getInt(PRIVILEGE)));
                    gradeReport.setAttestatMark(resultSet.getDouble(ATTESTAT_MARK));
                    gradeReport.setAverageMark(resultSet.getDouble(AVERAGE_MARK));
                    gradeReport.setMarks(new HashMap<>());
                }
                gradeReport.getMarks().put(Subject.getSubjectById(resultSet.getInt(SUBJECT_ID)), resultSet.getInt(MARK));
            }
        } catch (SQLException | DAOException e) {
            LOGGER.warn(e);
            throw new CreateObjectException(e);
        }
        return new HashSet<>(gradeReportMap.values());
    }
}
