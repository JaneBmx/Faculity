package com.vlasova.service.accept;

import com.vlasova.dao.gradereport.GradeReportDAO;
import com.vlasova.dao.gradereport.GradeReportDAOImpl;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.exception.dao.DAOException;
import com.vlasova.exception.service.ServiceException;
import com.vlasova.service.FacultyService;
import com.vlasova.service.GradeReportService;
import com.vlasova.service.comparator.GradeReportComparatorByMarks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Accepter {
    private static final Logger LOGGER = LogManager.getLogger(Accepter.class);
    GradeReportDAOImpl gradeReportDAO = new GradeReportDAOImpl();
    private List<GradeReport> gradeReports;
    private List<Faculty> faculties;
    private final List<GradeReport> toBatch =new ArrayList<>();
    private int facultyID;

    /**
     * Gets all of GradeReports and Faculties.
     * Set GradeReports isAccept and isFree
     * according Faculty accept plan
     * @see Faculty
     * @see GradeReport
     * @throws ServiceException if db data incorrect
     */
    public void enroll() throws ServiceException {

        int freePlan;
        int paidPlan;
        getData();
        for (Faculty faculty : faculties) {
            facultyID = faculty.getId();
            freePlan = faculty.getFreeAcceptPlan();
            paidPlan = faculty.getPaidAcceptPlan();
            List<GradeReport> byFaculty = gradeReports.stream().filter(g -> g.getFaculty()
                    .getId() == facultyID).sorted(new GradeReportComparatorByMarks())
                    .collect(Collectors.toList());
            acceptToFaculty(byFaculty, paidPlan + freePlan);
            acceptToFree(byFaculty, freePlan);
            byFaculty.removeIf(list -> !list.isAccepted());
            toBatch.addAll(byFaculty);
        }
        try {
            gradeReportDAO.enroll(toBatch);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void acceptToFaculty(List<GradeReport> current, int limitAccept) {
        Iterator<GradeReport> iterator = current.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            if (counter < limitAccept + 1)
                iterator.next().setAccepted(true);
            counter++;
        }
    }

    private void acceptToFree(List<GradeReport> current, int limitToFree) {
        Iterator<GradeReport> iterator = current.iterator();
        int counter = 0;
        while (iterator.hasNext()) {
            counter++;
            if (counter < limitToFree + 1)
                iterator.next().setFree(true);
            counter++;
        }
    }

    private void getData() {
        try {
            gradeReports = GradeReportService.getInstance().getAllGradeReports();
            faculties = FacultyService.getInstance().getAllFaculties();
        } catch (ServiceException e) {
            LOGGER.warn("Cant get init data.", e);
        }
    }
}
