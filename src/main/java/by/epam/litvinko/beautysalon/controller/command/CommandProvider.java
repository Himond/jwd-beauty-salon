package by.epam.litvinko.beautysalon.controller.command;

import by.epam.litvinko.beautysalon.controller.command.impl.*;
import by.epam.litvinko.beautysalon.controller.command.impl.go.GoToEditPasswordPageCommand;
import by.epam.litvinko.beautysalon.controller.command.impl.go.GoToEditProfilePageCommand;
import by.epam.litvinko.beautysalon.controller.command.impl.go.GoToForgotPasswordPageCommand;
import by.epam.litvinko.beautysalon.controller.command.impl.go.GoToSignUpPageCommand;

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
        commands.put(CommandType.FORGOT_PASSWORD, new ForgotPasswordCommand());
        commands.put(CommandType.EDIT_PASSWORD, new EditPasswordCommand());
        commands.put(CommandType.EDIT_PROFILE, new EditProfileCommand());
        commands.put(CommandType.EDIT_PHOTO, new EditPhotoCommand());
        commands.put(CommandType.GO_TO_EDIT_PASSWORD_PAGE, new GoToEditPasswordPageCommand());
        commands.put(CommandType.GO_TO_EDIT_PROFILE_PAGE, new GoToEditProfilePageCommand());
        commands.put(CommandType.GO_TO_FORGOT_PASSWORD_PAGE, new GoToForgotPasswordPageCommand());
        commands.put(CommandType.GO_TO_SIGNUP_PAGE, new GoToSignUpPageCommand());
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
