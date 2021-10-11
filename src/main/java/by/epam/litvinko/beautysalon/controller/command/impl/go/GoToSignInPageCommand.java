package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.LOGIN_JSP;

/**
 * The type Go to sign in page command.
 */
public class GoToSignInPageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(LOGIN_JSP, Router.RouterType.REDIRECT);
        return router;
    }
}
