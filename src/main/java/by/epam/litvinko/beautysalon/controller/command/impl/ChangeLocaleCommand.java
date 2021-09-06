package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ChangeLocaleCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        final HttpSession session = request.getSession();
        final String locale = request.getParameter(LOCALE);
        final String previous_request = (String) session.getAttribute(PREV_REQUEST);
        session.setAttribute(LOCALE_ATTRIBUTE, locale);
        Router router = new Router(previous_request, Router.RouterType.REDIRECT);
        return router;
    }

}
