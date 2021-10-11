package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.CONTACTS_JSP;


/**
 * The type Go to contacts page command.
 */
public class GoToContactsPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router(CONTACTS_JSP, Router.RouterType.REDIRECT);
        return router;
    }

}
