package by.epam.litvinko.beautysalon.command;

import by.epam.litvinko.beautysalon.command.impl.*;

import java.util.EnumMap;

public class CommandProvider {

    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.SIGN_IN, new SignInCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.LOG_OUT, new LogOutCommand());
        commands.put(CommandType.SIGN_UP, new SignUpCommand());
        commands.put(CommandType.CHANGE_LOCALE, new ChangeLocaleCommand());
        commands.put(CommandType.FORGET_PASSWORD, new ForgetPasswordCommand());
    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        if (commandName == null) {
            return commands.get(CommandType.DEFAULT);
        }
        CommandType commandType;
        try {
            commandType = CommandType.valueOf(commandName.toUpperCase());
        } catch (IllegalArgumentException e) {
            commandType = CommandType.DEFAULT;
        }
        return commands.get(commandType);
    }
}
