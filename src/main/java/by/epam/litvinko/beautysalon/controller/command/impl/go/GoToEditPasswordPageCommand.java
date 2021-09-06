package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.EDIT_PASSWORD_JSP;

public class GoToEditPasswordPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        Router router = new Router(EDIT_PASSWORD_JSP, Router.RouterType.REDIRECT);
        return router;
    }
}
