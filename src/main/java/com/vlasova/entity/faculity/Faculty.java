package com.vlasova.entity.faculity;

import java.util.Map;
import java.util.Objects;

public class Faculty {
    private int id;
    private String name;
    private int freeAcceptPlan;
    private int paidAcceptPlan;
    private Map<Subject, Integer> subjects;

    public Faculty() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFreeAcceptPlan() {
        return freeAcceptPlan;
    }

    public void setFreeAcceptPlan(int freeAcceptPlan) {
        this.freeAcceptPlan = freeAcceptPlan;
    }

    public int getPaidAcceptPlan() {
        return paidAcceptPlan;
    }

    public void setPaidAcceptPlan(int paidAcceptPlan) {
        this.paidAcceptPlan = paidAcceptPlan;
    }

    public Map<Subject, Integer> getSubjects() {
        return subjects;
    }

    public void setSubjects(Map<Subject, Integer> subjects) {
        this.subjects = subjects;
    }

    public int getAcceptPlan() {
        return paidAcceptPlan + freeAcceptPlan;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Faculty faculty = (Faculty) o;
        return id == faculty.id &&
                freeAcceptPlan == faculty.freeAcceptPlan &&
                paidAcceptPlan == faculty.paidAcceptPlan &&
                name.equals(faculty.name) &&
                subjects.equals(faculty.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, freeAcceptPlan, paidAcceptPlan, subjects);
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", freeAcceptPlan=" + freeAcceptPlan +
                ", paidAcceptPlan=" + paidAcceptPlan +
                ", subjects=" + subjects.toString() +
                '}';
    }
}
