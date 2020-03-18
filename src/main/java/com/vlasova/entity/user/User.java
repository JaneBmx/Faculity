package com.vlasova.entity.user;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private int id;
    Role role;
    private String name;
    private String surname;
    private String email;
    private String login;
    private char[] password;
    Privilege privilege;

    public User() {
    }

    public User(int id, Role role, String name, String surname, String email, String login, char[] password, Privilege privilege) {
        this.id = id;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
        this.privilege = privilege;
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

    public char[] getPassword() {
        return password;
    }

    public void setPassword(char[] password) {
        this.password = password;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
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
                Arrays.equals(password, user.password) &&
                privilege == user.privilege;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, role, name, surname, email, login, privilege);
        result = 31 * result + Arrays.hashCode(password);
        return result;
    }
}

