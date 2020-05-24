package com.vlasova.command.mapper;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.Privilege;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class GradeReportMapper {
    private static final String FACULTY_ID = "faculty_id";
    private static final String PRIVILEGE = "privilege";
    private static final String ATTESTAT = "attestat_mark";
    private static final String MARK_1 = "mark_1";
    private static final String MARK_2 = "mark_2";
    private static final String MARK_3 = "mark_3";

    public GradeReport map(HttpServletRequest request) {
        GradeReport gradeReport = new GradeReport();
        gradeReport.setFaculty(mapFaculty(request));
        gradeReport.setPrivilege(mapPrivilege(request));
        gradeReport.setAttestatMark(mapAttestatMark(request));
        gradeReport.setMarks(mapMarks(request));
        gradeReport.setAverageMark(mapMiddleMark(request));
        return gradeReport;
    }

    /**
     * Creating empty faculty with the only field 'id' for writing to DB
     */
    private Faculty mapFaculty(HttpServletRequest request) {
        Faculty faculty = new Faculty();
        int facultyId = Integer.parseInt(request.getParameter(FACULTY_ID));
        faculty.setId(facultyId);
        return faculty;
    }

    private Privilege mapPrivilege(HttpServletRequest request) {
        return Privilege.getPrivilegeById(Integer.parseInt(request.getParameter(PRIVILEGE)));
    }

    private double mapAttestatMark(HttpServletRequest request) {
        return Double.parseDouble(request.getParameter(ATTESTAT));
    }

    private Map<Subject, Integer> mapMarks(HttpServletRequest request) {
        //TODO make method
        return new HashMap<>();
    }

    private double mapMiddleMark(HttpServletRequest request) {
        int mark1 = Integer.parseInt(request.getParameter(MARK_1));
        int mark2 = Integer.parseInt(request.getParameter(MARK_2));
        int mark3 = Integer.parseInt(request.getParameter(MARK_3));
        return ((double) (mark1 + mark2 + mark3)) / 3;
    }
}