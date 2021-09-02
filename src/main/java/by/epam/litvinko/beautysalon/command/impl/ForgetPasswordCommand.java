package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.*;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class ForgetPasswordCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        final UserService userService = new UserServiceImpl();
        Router router;
        String email = request.getParameter(RequestParameter.EMAIL);
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        if (!userService.isEmailValid(email)){
            request.setAttribute(RequestParameter.EMAIL, email);
            request.getSession().setAttribute(RequestAttribute.EMAIL_NOT_EXISTS, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.EMAIL_NOT_EXISTS_PATH));
            return new Router(PagePath.CHANGE_PASSWORD_PAGE, Router.RouterType.FORWARD);
        }

        try {
            if (userService.forgetPassword(email)) {
                request.getSession().setAttribute(RequestAttribute.EMAIL_SENT,  MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.EMAIL_SENT_PATH));

            } else {
                request.setAttribute(RequestParameter.EMAIL, email);
                request.getSession().setAttribute(RequestAttribute.EMAIL_NOT_EXISTS, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.EMAIL_NOT_EXISTS_PATH));
                return new Router(PagePath.CHANGE_PASSWORD_PAGE, Router.RouterType.FORWARD);
            }
            router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ForgetPasswordCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;


    }


}
