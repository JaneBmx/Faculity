package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.user.*;

public enum CommandType {
    LOGIN(new SignInCommand()),
    EDIT_USER(new EditUserCommand()),


    SIGN_UP(new SignUpCommand()),
    SIGN_OUT(new LogOutCommand()),
    GET_ALL_USERS(new GetAllUsersCommand());

    private final Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
