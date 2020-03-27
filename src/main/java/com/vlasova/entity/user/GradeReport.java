package com.vlasova.entity.user;

import com.vlasova.entity.faculity.Subject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class GradeReport {
    private long id;
    private int userId;
    private double certificateMark;
    private boolean isAccepted;
    private boolean isFree;
    private final Map<Subject, Integer> marks = new HashMap<>();

    public GradeReport() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getCertificateMark() {
        return certificateMark;
    }

    public void setCertificateMark(double certificateMark) {
        this.certificateMark = certificateMark;
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
                userId == that.userId &&
                Double.compare(that.certificateMark, certificateMark) == 0 &&
                isAccepted == that.isAccepted &&
                isFree == that.isFree &&
                Objects.equals(marks, that.marks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, certificateMark, marks, isAccepted, isFree);
    }

    @Override
    public String toString() {
        return "GradeReport{" +
                "id=" + id +
                ", userId=" + userId +
                ", certificate=" + certificateMark +
                ", marks=" + marks +
                ", isAccepted=" + isAccepted +
                ", isFree=" + isFree +
                '}';
    }
}
