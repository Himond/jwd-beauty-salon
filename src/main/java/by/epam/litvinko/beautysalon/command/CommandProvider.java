package by.epam.litvinko.beautysalon.command;

import by.epam.litvinko.beautysalon.command.impl.DefaultCommand;
import by.epam.litvinko.beautysalon.command.impl.LogOutCommand;
import by.epam.litvinko.beautysalon.command.impl.SignInCommand;

import java.util.EnumMap;

public class CommandProvider {

    private static CommandProvider instance;
    private final EnumMap<CommandType, Command> commands = new EnumMap(CommandType.class);

    public CommandProvider() {
        commands.put(CommandType.SIGN_IN_COMMAND, new SignInCommand());
        commands.put(CommandType.DEFAULT, new DefaultCommand());
        commands.put(CommandType.LOG_OUT_COMMAND, new LogOutCommand());

    }

    public static CommandProvider getInstance() {
        if (instance == null) {
            instance = new CommandProvider();
        }
        return instance;
    }

    public Command getCommand(String commandName) {
        System.out.println("Command provider");
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
