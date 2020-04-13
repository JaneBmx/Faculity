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

    public static boolean isValidUserForRegistration(User user) {
        return isValidName(user.getName()) &&
            isValidName(user.getSurname()) &&
            isValidEmail(user.getEmail()) &&
            isValidLogin(user.getLogin()) &&
            isValidPassword(user.getPassword());
    }

    public static boolean isNotValidUserForRegistration(User user) {
        return !isValidUserForRegistration(user);
    }

    private static boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (isValidString(email)) {
            pattern = Pattern.compile(EMAIL_FORMAT);
            matcher = pattern.matcher(email);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidLogin(String login) {
        if (isValidString(login)) {
            pattern = Pattern.compile(LOGIN_FORMAT);
            matcher = pattern.matcher(login);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidName(String name) {
        if (isValidString(name)) {
            pattern = Pattern.compile(NAME_FORMAT);
            matcher = pattern.matcher(name);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        if (isValidString(password)) {
            pattern = Pattern.compile(PASS_FORMAT);
            matcher = pattern.matcher(password);
            return matcher.matches();
        }
        return false;
    }

    public static boolean isValidUser(User user) {
        if (user != null) {
            return user.getRole() != null
                && isValidName(user.getName())
                && isValidName(user.getSurname())
                && isValidEmail(user.getEmail())
                && isValidLogin(user.getLogin())
                && isValidPassword(user.getPassword());
        }
        return false;
    }
}
