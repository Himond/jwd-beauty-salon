package by.epam.litvinko.beautysalon.controller.command;

import by.epam.litvinko.beautysalon.controller.command.impl.*;
import by.epam.litvinko.beautysalon.controller.command.impl.go.*;

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
        commands.put(CommandType.PRODUCT_DETAIL, new ProductDetailCommand());
        commands.put(CommandType.ADD_TO_CART, new AddToCartCommand());
        commands.put(CommandType.ADD_COUPON, new AddCouponCommand());
        commands.put(CommandType.REMOVE_PRODUCT, new RemoveProductFromCartCommand());
        commands.put(CommandType.CREATED_ORDER, new CreateOrderCommand());
        commands.put(CommandType.GO_TO_CART_PAGE, new GoToCartPageCommand());
        commands.put(CommandType.GO_TO_EDIT_PASSWORD_PAGE, new GoToEditPasswordPageCommand());
        commands.put(CommandType.GO_TO_EDIT_PROFILE_PAGE, new GoToEditProfilePageCommand());
        commands.put(CommandType.GO_TO_FORGOT_PASSWORD_PAGE, new GoToForgotPasswordPageCommand());
        commands.put(CommandType.GO_TO_SIGNUP_PAGE, new GoToSignUpPageCommand());
        commands.put(CommandType.GO_TO_SHOP_PAGE, new GoToShopPageCommand());
        commands.put(CommandType.GO_TO_CONTACTS_PAGE, new GoToContactsPageCommand());
        commands.put(CommandType.GO_TO_MAIN_PAGE, new GoToMainPageCommand());
        commands.put(CommandType.GO_TO_PROFILE_PAGE, new GoToProfilePageCommand());
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
