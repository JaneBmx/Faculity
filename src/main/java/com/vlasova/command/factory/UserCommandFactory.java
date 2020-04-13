package com.vlasova.command.factory;

import com.vlasova.command.impl.user.DeleteUserCommand;
import com.vlasova.command.impl.user.EditUserCommand;
import com.vlasova.command.impl.user.GetUserByIdCommand;
import com.vlasova.command.impl.user.GetUsersByRolesCommand;
import com.vlasova.command.impl.user.LogInUserCommand;
import com.vlasova.command.impl.user.RegisterUserCommand;
import com.vlasova.command.impl.user.UserCommand;

public enum UserCommandFactory {
    REGISTRATION(new RegisterUserCommand()),
    LOG_IN(new LogInUserCommand()),
    EDIT_USER(new EditUserCommand()),
    DELETE_USER(new DeleteUserCommand()),
    GET_USERS_BY_ROLE(new GetUsersByRolesCommand()),
    GET_USER_BY_ID(new GetUserByIdCommand());

    private UserCommand userCommand;

    UserCommandFactory(UserCommand userCommand) {
        this.userCommand = userCommand;
    }

    public UserCommand getUserCommand() {
        return userCommand;
    }
}
