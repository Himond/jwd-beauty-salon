package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import by.epam.litvinko.beautysalon.controller.command.Router.RouterType;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
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
        String username = request.getParameter(USERNAME);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        String password = request.getParameter(PASSWORD);
        String passwordRep = request.getParameter(PASSWORD_REP);
        ClientService service = new ClientServiceImpl();

        Map<String, String> mapDataForm = service.isFormValid(username, firstName, lastName, email, phone, password);
        if (mapDataForm.containsValue(EMPTY_STRING)) {
            request.setAttribute(MAP_DATA_FORM, mapDataForm);
            request.setAttribute(WRONG_DATA_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_DATA_SING_UP_PATH));
            return new Router(PagePath.SIGNUP_JSP, RouterType.FORWARD);
        }
        if (!service.isPasswordsEquals(password, passwordRep)){
            request.setAttribute(MAP_DATA_FORM, mapDataForm);
            request.getSession().setAttribute(WRONG_PASSWORD_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_PASSWORD_SING_UP_PATH));
            return new Router(PagePath.SIGNUP_JSP, RouterType.FORWARD);
        }
        try {
            Optional<ClientDto> optionalClient = service.signUp(username, firstName, lastName, email, phone, password);
            if (optionalClient.isPresent()) {
                ClientDto client = optionalClient.get();
                request.getSession().setAttribute(USER, client);
                request.getSession().setAttribute(ROLE, client.role());
            }else {
                request.getSession().setAttribute(WRONG_LOGIN_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_LOGIN_SING_UP_PATH));
            }
            router = new Router(SIGNUP_JSP, RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, RouterType.REDIRECT);
        }
        return router;

    }
}
