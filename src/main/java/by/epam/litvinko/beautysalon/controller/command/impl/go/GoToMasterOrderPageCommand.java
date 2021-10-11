package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

/**
 * The type Go to master order page command.
 */
public class GoToMasterOrderPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToMasterOrderPageCommand.class);
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        try {
            MasterDto master = (MasterDto) request.getSession().getAttribute(USER);
            Map<Integer, List<Object>> newOrderItem = orderService.findOrderByMasterId(master.masterId());
            request.getSession().setAttribute(NEW_ORDER_ITEM, newOrderItem);
            router = new Router(MASTER_ORDERS_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at GoToMasterOrderPageCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }

}
