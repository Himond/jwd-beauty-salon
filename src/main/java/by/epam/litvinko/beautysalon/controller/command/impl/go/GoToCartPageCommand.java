package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.CART_DETAIL_JSP;

/**
 * The type Go to cart page command.
 */
public class GoToCartPageCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router = new Router(CART_DETAIL_JSP, Router.RouterType.REDIRECT);
        return router;
    }

}
