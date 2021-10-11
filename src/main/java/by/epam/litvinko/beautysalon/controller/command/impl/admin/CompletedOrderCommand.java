package by.epam.litvinko.beautysalon.controller.command.impl.admin;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.ADMIN_ORDERS_JSP;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.ERROR_JSP;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;


/**
 * The type Completed order command.
 */
public class CompletedOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CompletedOrderCommand.class);
    private final OrderService service = new OrderServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        String orderId = request.getParameter(ORDER_ID);
        try {
            if (service.updateActiveOrder(Integer.parseInt(orderId))) {
                Map<Integer, List<Object>> newOrderItem = service.findAllOrderForAdmin();
                request.getSession().setAttribute(NEW_ORDER_ITEM, newOrderItem);
            }else {
                request.getSession().setAttribute(FAILED_OPERATION, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(FAILED_OPERATION_PATH));
            }
            router = new Router(ADMIN_ORDERS_JSP, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at CompletedOrderCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }

}
