package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.Command;
import by.epam.litvinko.beautysalon.command.PagePath;
import by.epam.litvinko.beautysalon.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        request.getSession(false).invalidate();
        Router router = new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
        return router;
    }

}
