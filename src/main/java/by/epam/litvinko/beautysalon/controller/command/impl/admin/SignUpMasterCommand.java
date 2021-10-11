package by.epam.litvinko.beautysalon.controller.command.impl.admin;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.PagePath;
import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.MasterServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.WRONG_PASSWORD_SING_UP_PATH;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;

/**
 * The type Sign up master command.
 */
public class SignUpMasterCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignUpMasterCommand.class);
    private final MasterService masterService = new MasterServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);
        String username = request.getParameter(USERNAME);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String description = request.getParameter(NEW_MASTER_DESCRIPTION);
        String position = request.getParameter(SELECT_POSITION);
        String password = request.getParameter(PASSWORD);
        String passwordRep = request.getParameter(PASSWORD_REP);

        if (!clientService.isPasswordsEquals(password, passwordRep)){
            request.getSession().setAttribute(WRONG_PASSWORD_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_PASSWORD_SING_UP_PATH));
            return new Router(PagePath.ADMIN_JSP, Router.RouterType.FORWARD);
        }
        try {
            if (!masterService.signUp(username, firstName, lastName, email, description, position, password)) {
                request.getSession().setAttribute(MASTER_NOT_ADDED, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(MASTER_NOT_ADDED_PATH));
            }
            request.getSession().setAttribute(MASTER_ADDED_SUCCESSFULLY, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(MASTER_ADDED_SUCCESSFULLY_PATH));
            router = new Router(ADMIN_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at SignUpMasterCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;

    }

}
