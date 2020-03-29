package com.vlasova.entity.user;

import com.vlasova.entity.faculity.Faculties;
import com.vlasova.entity.faculity.Faculty;
import com.vlasova.entity.faculity.Subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GradeReport {
    private int id;
    private double certificate;
    private boolean isAccepted;
    private boolean isFree;
    private final Map<Subject, Integer> marks;
    private Faculty faculty;

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        if (Faculties.FACULTIES.isExist(faculty)) {
            this.faculty = faculty;
        }
    }

    public GradeReport() {
        marks = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCertificate() {
        return certificate;
    }

    public void setCertificate(double certificate) {
        this.certificate = certificate;
    }

    public Map<Subject, Integer> getMarks() {
        return marks;
    }

    public void addMarks(Subject subject, Integer mark) {
        marks.put(subject, mark);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeReport that = (GradeReport) o;
        return id == that.id &&
                Double.compare(that.certificate, certificate) == 0 &&
                isAccepted == that.isAccepted &&
                isFree == that.isFree &&
                Objects.equals(marks, that.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, certificate, marks, isAccepted, isFree);
    }

    @Override
    public String toString() {
        return "GradeReport{" +
                "id=" + id +
                ", certificate=" + certificate +
                ", marks=" + marks +
                ", isAccepted=" + isAccepted +
                ", isFree=" + isFree +
                '}';
    }
}
