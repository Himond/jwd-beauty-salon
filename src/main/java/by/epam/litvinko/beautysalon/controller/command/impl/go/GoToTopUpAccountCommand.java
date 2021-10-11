package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.TOP_UP_AN_ACCOUNT_JSP;

/**
 * The type Go to top up account command.
 */
public class GoToTopUpAccountCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router(TOP_UP_AN_ACCOUNT_JSP, Router.RouterType.REDIRECT);
        return router;
    }
}
