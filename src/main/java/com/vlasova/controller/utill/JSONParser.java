package com.vlasova.controller.utill;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;
import com.vlasova.controller.utill.validator.FacultyValidator;
import com.vlasova.controller.utill.validator.GradeReportValidator;
import com.vlasova.controller.utill.validator.UserDataValidator;
import java.util.List;
import java.util.Map;

public class JSONParser {
    private final FacultyValidator fValidator = new FacultyValidator();
    private final GradeReportValidator gRValidator = new GradeReportValidator();
    private final UserDataValidator uValidator = new UserDataValidator();

    public String parseUserListToJSON(List<User> list) {
        list.removeIf(user -> (!uValidator.isValidUser(user)));
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User u : list) {
            sb.append("{");
            sb.append("\"id\": \"" + u.getId() + "\",");
            sb.append("\"role\": \"" + u.getRole().name() + "\",");
            sb.append("\"name\": \"" + u.getName() + "\",");
            sb.append("\"surname\": \"" + u.getSurname() + "\",");
            sb.append("\"email\": \"" + u.getEmail() + "\",");
            sb.append("\"login\": \"" + u.getLogin() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseFacultyListToJSON(List<Faculty> list) {
        list.removeIf(faculty -> (!fValidator.isValidFaculty(faculty)));
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Faculty f : list) {
            sb.append("{");
            sb.append("\"id\": \"" + f.getId() + "\",");
            sb.append("\"name\": \"" + f.getName() + "\",");
            int count = 1;
            for (Subject s : f.getSubjects()) {
                sb.append("\"sub" + count + "\": \"" + s.getName() + "\",");
                count++;
            }
            sb.append("\"free\": \"" + f.getFreeAcceptPlan() + "\",");
            sb.append("\"paid\": \"" + f.getPaidAcceptPlan() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseGradeReportListToJSON(List<GradeReport> list) {
        list.removeIf(gr -> (!gRValidator.isValidGradeReport(gr)));
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (GradeReport g : list) {
            sb.append("{");
            sb.append("\"id\": \"" + g.getId() + "\",");
            sb.append("\"faculty\": \"" + g.getFaculty().getName() + "\",");
            sb.append("\"isAccept\": \"" + g.isAccepted() + "\",");
            sb.append("\"isFree\": \"" + g.isFree() + "\",");
            sb.append("\"privilege\": \"" + g.getPrivilege().getName() + "\",");
            sb.append("\"attestat\": \"" + g.getAttestatMark() + "\",");
            sb.append("\"average\": \"" + g.getAverageMark() + "\"");
            sb.append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseGradeReportListToJSON(Map<String, Integer> info){
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append("{");
        for (Map.Entry<String, Integer> entry : info.entrySet()) {
            sb.append("\"" +entry.getKey() +"\": \"" + entry.getValue() + "\",");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("}");
        sb.append("]");
        return sb.toString();
    }
}