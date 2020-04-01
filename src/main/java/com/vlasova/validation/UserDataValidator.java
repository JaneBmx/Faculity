package com.vlasova.validation;

import com.vlasova.entity.user.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserDataValidator {
    private static Pattern pattern;
    private static Matcher matcher;
    private static final String PASS_FORMAT = "((?=.*\\d)(?=.*[a-z]).{8,40})";
    private static final String NAME_FORMAT = "[a-zA-Z\\._\\-]{3,40}";
    private static final String EMAIL_FORMAT = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String LOGIN_FORMAT = "[a-zA-Z\\d]{4,40}";

    private UserDataValidator() {
    }

    public static boolean isValidEmail(String email) {
        pattern = Pattern.compile(EMAIL_FORMAT);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidLogin(String login) {
        pattern = Pattern.compile(LOGIN_FORMAT);
        matcher = pattern.matcher(login);
        return matcher.matches();
    }

    public static boolean isValidName(String name) {
        pattern = Pattern.compile(NAME_FORMAT);
        matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        pattern = Pattern.compile(PASS_FORMAT);
        matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isValidUser(User user) {
        return isValidEmail(user.getEmail()) && isValidLogin(user.getLogin())
                && isValidName(user.getName()) && isValidName(user.getSurname())
                && isValidPassword(user.getPassword());
    }
}
