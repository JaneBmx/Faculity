package com.vlasova.specification.user;

import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public abstract class AbstractUserSpecification {
    protected static final String ID = "user_id";
    protected static final String ROLE = "role";
    protected static final String NAME = "name";
    protected static final String SURNAME = "surname";
    protected static final String EMAIL = "email";
    protected static final String LOGIN = "login";
    protected static final String PASSWORD = "password"; //TODO make more secure
    protected static final String PRIVELEGE = "privelege";
    protected static final String GRADEREPORT = "gradereport";
    ResultSet resultSet;
    Set<User> users;

    protected void closeResultSet() {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                //TODO own exception
            }
        }
    }

    protected User createUser() {
        User user = null;
        try {
            if (resultSet != null) {
                user = new User();
                user.setId(resultSet.getInt(ID));
                user.setRole(Role.getRole(resultSet.getInt(ROLE)));
                user.setName(resultSet.getString(NAME));
                user.setSurname(resultSet.getString(SURNAME));
                user.setEmail(resultSet.getString(EMAIL));
                user.setLogin(resultSet.getString(LOGIN));
                user.setPassword(resultSet.getString(PASSWORD));
            }
        } catch (SQLException e) {
            //TODO log
        }
        return user;
    }
}