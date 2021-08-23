package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.Command;
import by.epam.litvinko.beautysalon.command.PagePath;
import by.epam.litvinko.beautysalon.command.Router;

import javax.servlet.http.HttpServletRequest;

public class LogOutCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(PagePath.LOGIN_PAGE, Router.RouterType.REDIRECT);
        return router;
    }

}
