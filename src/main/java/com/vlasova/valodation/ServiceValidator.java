package com.vlasova.valodation;

import com.vlasova.entity.faculity.Subject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServiceValidator {
    private Pattern pattern;
    private Matcher matcher;
    private static final String PASS_FORMAT = "((?=.*\\d)(?=.*[a-z]).{6,45})";
    private static final String NAME_FORMAT = "[a-zA-Z\\._\\-]{3,45}";
    private static final String EMAIL_FORMAT = "^[_A-Za-z\\d-\\+]+(\\.[_A-Za-z\\d-]+)*@[A-Za-z\\d-]+(\\.[A-Za-z\\d]+)*(\\.[A-Za-z]{2,})$";
    private static final String LOGIN_FORMAT = "[a-zA-Z\\d]{3,45}";


    public boolean isValidFaculty(String name, int free, int paid, Subject... subjects) {
        int countOfValidSubjects = 0;
        for (Subject s : subjects) {
            if (s != null) {
                countOfValidSubjects++;
            }
        }
        return name != null && !name.isEmpty() && free >= 0 && paid >= 0
                && free + paid > 0 && countOfValidSubjects > 0;
    }

    public boolean isValidUser(String name, String surname, String email, String login, String password) {
        return isEmail(email) && isLogin(login) && isName(name) && isName(surname) && isPassword(password);
    }

    public boolean isEmail(String email) {
        pattern = Pattern.compile(EMAIL_FORMAT);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean isLogin(String userLogin) {
        pattern = Pattern.compile(LOGIN_FORMAT);
        matcher = pattern.matcher(userLogin);
        return matcher.matches();
    }

    public boolean isName(String userName) {
        pattern = Pattern.compile(NAME_FORMAT);
        matcher = pattern.matcher(userName);
        return matcher.matches();
    }

    public boolean isPassword(String userPassword) {
        pattern = Pattern.compile(PASS_FORMAT);
        matcher = pattern.matcher(userPassword);
        return matcher.matches();
    }
}
