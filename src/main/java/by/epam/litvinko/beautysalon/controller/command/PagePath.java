package by.epam.litvinko.beautysalon.controller.command;

public final class PagePath {

    public static final String ERROR_404_JSP = "/jsp/error/404.jsp";
    public static final String LOGIN_JSP = "jsp/login.jsp";
    public static final String PROFILE_JSP = "jsp/profile.jsp";
    public static final String FORGOT_PASSWORD_JSP = "jsp/forgot_password.jsp";
    public static final String EDIT_PROFILE_JSP = "jsp/edit_profile.jsp";
    public static final String EDIT_PASSWORD_JSP = "jsp/edit_password.jsp";
    public static final String SIGNUP_JSP = "jsp/signup.jsp";
    public static final String MAIN_JSP = "jsp/main.jsp";
    public static final String INDEX_JSP = "index.jsp";
    public static final String ERROR_JSP = "/jsp/error/error.jsp";




    public static final String LOG_OUT = "Controller?command=log_out";// FIXME: 25.08.2021
    public static final String GO_TO_LOGIN_PAGE = "Controller?command=go_to_login_page_command";
    private PagePath() {
    }
}