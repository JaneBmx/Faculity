package com.vlasova.mapper.dao;

import com.vlasova.entity.user.Privilege;
import com.vlasova.entity.user.Role;
import com.vlasova.entity.user.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserResultSetMapper {
    private static final Logger LOGGER = LogManager.getLogger(UserResultSetMapper.class);
    private static final String ID = "user_id";
    private static final String ROLE = "user_role";
    private static final String NAME = "user_name";
    private static final String SURNAME = "user_surname";
    private static final String EMAIL = "user_email";
    private static final String LOGIN = "user_login";
    private static final String PASSWORD = "user_password";
    private static final String PRIVILEGE = "user_privilege";

    public User map(ResultSet resultSet) {
        User user = null;
        try {
            user = new User();
            resultSet.next();
            user.setId(resultSet.getInt(ID));
            user.setRole(Role.getRole(resultSet.getInt(ROLE)));
            user.setName(resultSet.getString(NAME));
            user.setSurname(resultSet.getString(SURNAME));
            user.setEmail(resultSet.getString(EMAIL));
            user.setLogin(resultSet.getString(LOGIN));
            user.setPassword(resultSet.getString(PASSWORD));
            user.setPrivilege(Privilege.getPrivilegeById(resultSet.getInt(PRIVILEGE)));
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return user;
    }
}
