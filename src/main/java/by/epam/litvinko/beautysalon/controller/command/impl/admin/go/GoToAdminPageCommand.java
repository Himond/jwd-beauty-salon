package by.epam.litvinko.beautysalon.controller.command.impl.admin.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.Position;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.impl.MasterServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;

/**
 * The type Go to admin page command.
 */
public class GoToAdminPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToAdminPageCommand.class);
    private final MasterService masterService = new MasterServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        try {
            List<Position> allPosition = masterService.allPosition();
            request.getSession().setAttribute(POSITION_LIST, allPosition);
            router = new Router(ADMIN_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at GoToAdminPageCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }

}
