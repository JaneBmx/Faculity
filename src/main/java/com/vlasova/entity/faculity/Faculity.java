package com.vlasova.entity.faculity;

import java.util.Map;
import java.util.Objects;

public class Faculity {
    private int id;
    private String name;
    /**
     * Some huge text for describing why u should choose THIS faculity.
     */
    private String info;
    private int freeAcceptPlan;
    private int paidAcceptPlan;
    /**
     * Integer use for mark priority
     * if some subject have equals priority, then otorvat' Yane ruki
     */
    private Map<Subject, Integer> subjects;

    public Faculity() {
    }

    public Faculity(int id, String name, String info, int freeAcceptPlan, int paidAcceptPlan, Map<Subject, Integer> subjects) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.freeAcceptPlan = freeAcceptPlan;
        this.paidAcceptPlan = paidAcceptPlan;
        this.subjects = subjects;
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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
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
        Faculity faculity = (Faculity) o;
        return id == faculity.id &&
                freeAcceptPlan == faculity.freeAcceptPlan &&
                paidAcceptPlan == faculity.paidAcceptPlan &&
                name.equals(faculity.name) &&
                info.equals(faculity.info) &&
                subjects.equals(faculity.subjects);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, info, freeAcceptPlan, paidAcceptPlan, subjects);
    }

    @Override
    public String toString() {
        return "Faculity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", freeAcceptPlan=" + freeAcceptPlan +
                ", paidAcceptPlan=" + paidAcceptPlan +
                ", subjects=" + subjects.toString() +
                '}';
    }
}
