package com.vlasova.factory;

import com.vlasova.command.Command;
import com.vlasova.command.impl.user.DeleteUser;
import com.vlasova.command.impl.user.EditUser;
import com.vlasova.command.impl.user.LogIn;
import com.vlasova.command.impl.user.RegisterUser;

public enum CommandFactory {
    REGISTRATION(new RegisterUser()),
    LOG_IN(new LogIn()),
    EDIT_USER(new EditUser()),
    DELETE_USER(new DeleteUser());

    private Command command;

    private CommandFactory(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
