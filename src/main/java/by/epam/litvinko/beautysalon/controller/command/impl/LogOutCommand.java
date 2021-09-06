package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        Router router = new Router(MAIN_JSP, Router.RouterType.REDIRECT);
        return router;
    }

}
