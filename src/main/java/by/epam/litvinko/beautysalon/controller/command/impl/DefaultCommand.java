package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.PagePath;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

/**
 * The type Default command.
 */
public class DefaultCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        return new Router(PagePath.ERROR_404_JSP, Router.RouterType.REDIRECT);
    }
}
