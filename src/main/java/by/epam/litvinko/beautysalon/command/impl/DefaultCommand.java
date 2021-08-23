package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.Command;
import by.epam.litvinko.beautysalon.command.PagePath;
import by.epam.litvinko.beautysalon.command.Router;

import javax.servlet.http.HttpServletRequest;

public class DefaultCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404_PAGE, Router.RouterType.REDIRECT);
    }
}
