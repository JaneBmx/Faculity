package com.vlasova.util.validation;

import com.vlasova.entity.user.User;
import java.util.regex.Pattern;

public class UserDataValidator {
    private static final String PASS_FORMAT = "((?=.*\\d)(?=.*[a-z]).{6,40})";
    private static final String NAME_FORMAT = "([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$";
    private static final String EMAIL_FORMAT = "^((?!\\.)[\\w-_.]*[^.])(@\\w+)(\\.\\w+(\\.\\w+)?[^.\\W])$";
    private static final String LOGIN_FORMAT = "[a-zA-Z\\d]{4,40}";

    private boolean isValidString(String string) {
        return string != null && !string.isEmpty();
    }

    public boolean isValidEmail(String email) {
        if (isValidString(email)) {
            return Pattern.compile(EMAIL_FORMAT).matcher(email).matches();
        }
        return false;
    }

    public boolean isValidLogin(String login) {
        if (isValidString(login)) {
            return Pattern.compile(LOGIN_FORMAT).matcher(login).matches();
        }
        return false;
    }

    public boolean isValidName(String name) {
        if (isValidString(name)) {
            return Pattern.compile(NAME_FORMAT).matcher(name).matches();
        }
        return false;
    }

    public boolean isValidPassword(String password) {
        if (isValidString(password)) {
            return Pattern.compile(PASS_FORMAT).matcher(password).matches();
        }
        return false;
    }

    public boolean isValidUser(User user) {
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