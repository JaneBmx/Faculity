package com.vlasova.entity.user;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {
    private int id;
    private Role role;
    private String name;
    private String surname;
    private String email;
    private String login;
    private String password;
    private GradeReport gradeReport;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public GradeReport getGradeReport() {
        return gradeReport;
    }

    public void setGradeReport(GradeReport gradeReport) {
        this.gradeReport = gradeReport;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id &&
                role == user.role &&
                name.equals(user.name) &&
                surname.equals(user.surname) &&
                email.equals(user.email) &&
                login.equals(user.login) &&
                password.equals(user.password) &&
                gradeReport.equals(user.gradeReport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, role, name, surname, email, login, password, gradeReport);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gradeReport=" + gradeReport +
                '}';
    }
}

