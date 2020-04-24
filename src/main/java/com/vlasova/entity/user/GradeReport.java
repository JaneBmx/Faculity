package com.vlasova.entity.user;

import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class GradeReport implements Serializable {
    private int id;
    private Faculty faculty;
    private boolean isAccepted;
    private boolean isFree;
    private Privilege privilege;
    private double attestatMark;
    private double averageMark;
    private Map<Subject, Integer> marks;

    public GradeReport() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAttestatMark() {
        return attestatMark;
    }

    public void setAttestatMark(double attestatMark) {
        this.attestatMark = attestatMark;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }

    public Map<Subject, Integer> getMarks() {
        return marks;
    }

    public void setMarks(Map<Subject, Integer> marks) {
        this.marks = marks;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public double getAverageMark() {
        return averageMark;
    }

    public void setAverageMark(double averageMark) {
        this.averageMark = averageMark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeReport that = (GradeReport) o;
        return id == that.id &&
                isAccepted == that.isAccepted &&
                isFree == that.isFree &&
                Double.compare(that.attestatMark, attestatMark) == 0 &&
                Double.compare(that.averageMark, averageMark) == 0 &&
                faculty.equals(that.faculty) &&
                privilege == that.privilege &&
                marks.equals(that.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, faculty, isAccepted, isFree, privilege, attestatMark, averageMark, marks);
    }

    @Override
    public String toString() {
        return "GradeReport{" +
                "id=" + id +
                ", faculty=" + faculty +
                ", isAccepted=" + isAccepted +
                ", isFree=" + isFree +
                ", privilege=" + privilege +
                ", certificate=" + attestatMark +
                ", averageMark=" + averageMark +
                ", marks=" + marks +
                '}';
    }
}
