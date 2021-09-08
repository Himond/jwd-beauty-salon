package by.epam.litvinko.beautysalon.controller.command;

public class RequestAttribute {

    public static final String EXCEPTION = "exception";
    public static final String ROLE = "role";
    public static final String USER = "user";
    public static final String USER_ID = "user_id";


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
    public static final String LOCALE_ATTRIBUTE = "locale";

    public static final String EMAIL_SENT = "emailSent";
    public static final String EMAIL_NOT_EXISTS = "emailNotExists";
    public static final String CHANGE_PASSWORD_ERROR = "changePasswordError";
    public static final String EDIT_PASSWORD_SUCCESSFULLY = "editPassword";
    public static final String EDIT_PASSWORD_ERROR = "editPasswordError";
    public static final String EDIT_USER_DATA_ERROR = "editProfileError";



    public static final String EMAIL_SENT_PATH = "message.emailSent";
    public static final String EMAIL_NOT_EXISTS_PATH = "message.emailNotExists";
    public static final String CHANGE_PASSWORD_ERROR_PATH = "message.changePasswordError";
    public static final String EDIT_PASSWORD_SUCCESSFULLY_PATH = "message.editPassword";
    public static final String EDIT_PASSWORD_ERROR_PATH = "message.editPasswordError";
    public static final String EDIT_USER_DATA_ERROR_PATH = "message.editProfileError";


    private RequestAttribute() {
    }
}