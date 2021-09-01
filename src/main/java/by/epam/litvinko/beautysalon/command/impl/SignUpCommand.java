package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.*;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import by.epam.litvinko.beautysalon.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

public class SignUpCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);
    private static final String EMPTY_STRING = "";
    private static final String MAP_DATA_FORM = "mapData";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);
        String username = request.getParameter(RequestParameter.USERNAME);
        String firstName = request.getParameter(RequestParameter.FIRSTNAME);
        String lastName = request.getParameter(RequestParameter.LASTNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String phone = request.getParameter(RequestParameter.PHONE);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordRep = request.getParameter(RequestParameter.PASSWORD_REP);
        UserService service = new UserServiceImpl();

        Map<String, String> mapDataForm = service.isFormValid(username, password, firstName, lastName, email, phone);
        if (mapDataForm.containsValue(EMPTY_STRING)) {
            request.setAttribute(MAP_DATA_FORM, mapDataForm);
            request.setAttribute(RequestAttribute.WRONG_DATA_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.WRONG_DATA_SING_UP_PATH));
            return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.FORWARD);
        }
        if (!service.isPasswordsEquals(password, passwordRep)){
            request.setAttribute(MAP_DATA_FORM, mapDataForm);
            request.getSession().setAttribute(RequestAttribute.WRONG_PASSWORD_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.WRONG_PASSWORD_SING_UP_PATH));
            return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.FORWARD);
        }
        try {
            Optional<UserDto> optionalUser = service.signUp(username, password, firstName, lastName, email, phone);
            if (optionalUser.isPresent()) {
                UserDto user = optionalUser.get();
                request.getSession().setAttribute(RequestAttribute.USER, user);
                request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
            }else {
                request.getSession().setAttribute(RequestAttribute.WRONG_LOGIN_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.WRONG_LOGIN_SING_UP_PATH));
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
            }
        }catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;

    }
}
