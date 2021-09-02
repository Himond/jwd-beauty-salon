package by.epam.litvinko.beautysalon.command;

public final class PagePath {

    public static final String ERROR_404_PAGE = "/jsp/error/404.jsp";
    public static final String LOGIN_PAGE = "jsp/login.jsp";
    public static final String CHANGE_PASSWORD_PAGE = "jsp/password_change.jsp";

    public static final String SIGN_UP_PAGE = "jsp/signup.jsp";
    public static final String MAIN_PAGE = "jsp/main.jsp";
    public static final String INDEX_PAGE = "index.jsp";
    public static final String ERROR_PAGE = "/jsp/error/error.jsp";


    public static final String LOG_OUT = "Controller?command=log_out";// FIXME: 25.08.2021

    private PagePath() {
    }
}
