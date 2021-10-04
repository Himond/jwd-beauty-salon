package by.epam.litvinko.beautysalon.controller.command.impl;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;

import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;
import by.epam.litvinko.beautysalon.controller.command.Router.RouterType;
import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.UserService;
import by.epam.litvinko.beautysalon.model.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ForgotPasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger(ForgotPasswordCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        final UserService userService = new UserServiceImpl();
        Router router;
        String email = request.getParameter(EMAIL);
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        if (!userService.isEmailValid(email)){
            request.setAttribute(EMAIL, email);
            request.getSession().setAttribute(EMAIL_NOT_EXISTS, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EMAIL_NOT_EXISTS_PATH));
            return new Router(FORGOT_PASSWORD_JSP, RouterType.FORWARD);
        }

        try {
            if (userService.forgetPassword(email)) {
                request.getSession().setAttribute(EMAIL_SENT,  MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EMAIL_SENT_PATH));

            } else {
                request.setAttribute(EMAIL, email);
                request.getSession().setAttribute(EMAIL_NOT_EXISTS, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EMAIL_NOT_EXISTS_PATH));
                return new Router(FORGOT_PASSWORD_JSP, RouterType.FORWARD);
            }
            router = new Router(LOGIN_JSP, RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ForgotPasswordCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, RouterType.REDIRECT);
        }
        return router;


    }


}
