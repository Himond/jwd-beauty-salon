package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.EDIT_PROFILE_JSP;

/**
 * The type Go to edit profile page command.
 */
public class GoToEditProfilePageCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(EDIT_PROFILE_JSP, Router.RouterType.REDIRECT);
        return router;
    }
}
