package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.user.*;

public enum CommandType {
    SIGN_UP(new SignUpCommand()),
    SIGN_IN(new SignInCommand()),
    SIGN_OUT(new LogOutCommand());

    private Command command;

    CommandType(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
