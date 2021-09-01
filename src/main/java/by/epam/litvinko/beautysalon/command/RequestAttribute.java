package by.epam.litvinko.beautysalon.command;

public class RequestAttribute {

    public static final String EXCEPTION = "exception";
    public static final String ROLE = "role";
    public static final String USER = "user";


    public static final String WRONG_USERNAME_OR_PASSWORD_SING_IN = "errorLoginPassMessage";

    public static final String CONTROLLER_URL = "Controller?";

    public static final String WRONG_DATA_SING_UP = "wrongDataSignUp";
    public static final String WRONG_PASSWORD_SING_UP = "errorPasswordSignUp";
    public static final String WRONG_LOGIN_SING_UP = "errorLoginExists";

    public static final String WRONG_DATA_SING_UP_PATH = "message.signuwrongdata";
    public static final String WRONG_PASSWORD_SING_UP_PATH = "message.signuppassworderror";
    public static final String WRONG_LOGIN_SING_UP_PATH = "message.signuploginerror";
    public static final String WRONG_USERNAME_OR_PASSWORD_SING_IN_PATH = "message.loginerror";
    public static final String PREV_REQUEST = "current_page";
    public static final String LOCALE = "locale";

    private RequestAttribute() {
    }
}
