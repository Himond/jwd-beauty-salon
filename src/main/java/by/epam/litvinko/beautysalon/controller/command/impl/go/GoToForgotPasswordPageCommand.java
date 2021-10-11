package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.FORGOT_PASSWORD_JSP;

/**
 * The type Go to forgot password page command.
 */
public class GoToForgotPasswordPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(FORGOT_PASSWORD_JSP, Router.RouterType.REDIRECT);
        return router;
    }

}
