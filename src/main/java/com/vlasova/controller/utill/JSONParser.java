package com.vlasova.controller.utill;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;
import com.vlasova.entity.user.GradeReport;
import com.vlasova.entity.user.User;

import java.util.List;

public class JSONParser {

    public String parseUserListToJSON(List<User> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (User u : list) {
            sb.append("{").append("\"id\": \"" + u.getId() + "\",")
                    .append("\"role\": \"" + u.getRole().name() + "\",")
                    .append("\"name\": \"" + u.getName() + "\",")
                    .append("\"surname\": \"" + u.getSurname() + "\",")
                    .append("\"email\": \"" + u.getEmail() + "\",")
                    .append("\"login\": \"" + u.getLogin() + "\"")
                    .append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseFacultyListToJSON(List<Faculty> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (Faculty f : list) {
            sb.append("{")
                    .append("\"id\": \"" + f.getId() + "\",")
                    .append("\"name\": \"" + f.getName() + "\",");
            int count = 1;
            for (Subject s : f.getSubjects()) {
                sb.append("\"sub" + count + "\": \"" + s.getName() + "\",");
                count++;
            }
            sb.append("\"free\": \"" + f.getFreeAcceptPlan() + "\",")
                    .append("\"paid\": \"" + f.getPaidAcceptPlan() + "\"")
                    .append("},");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    public String parseGradeReportListToJSON(List<GradeReport> list) {
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
}
