package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.UserService;
import by.epam.litvinko.beautysalon.model.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;

public class EditPasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditPasswordCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        final UserService userService = new UserServiceImpl();
        Router router;
        String userId = request.getParameter(USER_ID);
        String oldPassword = request.getParameter(PASSWORD);
        String newPassword = request.getParameter(NEW_PASSWORD);
        String newPasswordRep = request.getParameter(PASSWORD_REP);
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        if (!userService.isPasswordsEquals(newPassword, newPasswordRep)){
            request.getSession().setAttribute(WRONG_PASSWORD_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_PASSWORD_SING_UP_PATH));
            return new Router(EDIT_PASSWORD_JSP, Router.RouterType.FORWARD);
        }
        try {
            if (userService.editPassword(userId, oldPassword, newPassword)) {
                request.getSession().setAttribute(EDIT_PASSWORD_SUCCESSFULLY,  MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EDIT_PASSWORD_SUCCESSFULLY_PATH));
            } else {
                request.getSession().setAttribute(EDIT_PASSWORD_ERROR, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EDIT_PASSWORD_ERROR_PATH));
                return new Router(EDIT_PASSWORD_JSP, Router.RouterType.REDIRECT);
            }
            router = new Router(PROFILE_JSP, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ForgetPasswordCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;


    }
}
