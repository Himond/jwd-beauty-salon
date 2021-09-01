package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.Command;
import by.epam.litvinko.beautysalon.command.RequestAttribute;
import by.epam.litvinko.beautysalon.command.RequestParameter;
import by.epam.litvinko.beautysalon.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String locale = request.getParameter(RequestParameter.LOCALE);
        final String previous_request = (String) session.getAttribute(RequestAttribute.PREV_REQUEST);
        session.setAttribute(RequestAttribute.LOCALE, locale);
        System.out.println(session.getAttribute(RequestAttribute.LOCALE));
        Router router = new Router(previous_request, Router.RouterType.REDIRECT);
        return router;
    }

}
