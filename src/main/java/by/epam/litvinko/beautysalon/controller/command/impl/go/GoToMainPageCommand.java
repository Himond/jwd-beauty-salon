package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.MAIN_JSP;

/**
 * The type Go to main page command.
 */
public class GoToMainPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router(MAIN_JSP, Router.RouterType.REDIRECT);
        return router;
    }

}
