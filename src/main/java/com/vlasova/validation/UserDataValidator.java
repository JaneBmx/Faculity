package com.vlasova.validation;

import com.vlasova.entity.user.User;

import java.util.regex.Pattern;

public class UserDataValidator {
    private static final String PASS_FORMAT = "((?=.*\\d)(?=.*[a-z]).{8,40})";
    private static final String NAME_FORMAT = "[a-zA-Z\\._\\-]{3,40}";
    private static final String EMAIL_FORMAT = "^([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}$";
    private static final String LOGIN_FORMAT = "[a-zA-Z\\d]{4,40}";

    private UserDataValidator() {
    }

    private static boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        if (isValidString(email)) {
            return Pattern.compile(EMAIL_FORMAT).matcher(email).matches();
        }
        return false;
    }

    public static boolean isValidLogin(String login) {
        if (isValidString(login)) {
            return Pattern.compile(LOGIN_FORMAT).matcher(login).matches();
        }
        return false;
    }

    public static boolean isValidName(String name) {
        if (isValidString(name)) {
            return Pattern.compile(NAME_FORMAT).matcher(name).matches();
        }
        return false;
    }

    public static boolean isValidPassword(String password) {
        if (isValidString(password)) {
            return Pattern.compile(PASS_FORMAT).matcher(password).matches();
        }
        return false;
    }

    public static boolean isValidUser(User user) {
        return true;
//        if (user != null) {
//            return user.getRole() != null
//                    && isValidName(user.getName())
//                    && isValidName(user.getSurname())
//                    && isValidEmail(user.getEmail())
//                    && isValidLogin(user.getLogin())
//                    && isValidPassword(user.getPassword());
//        }
       // return false;
    }
}
