package by.epam.litvinko.beautysalon.controller.command.impl.admin.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.impl.MasterServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.OrderServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
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
 * The type Go to admin order command.
 */
public class GoToAdminOrderCommand implements Command {
    private static final Logger logger = LogManager.getLogger(GoToAdminOrderCommand.class);
    private final OrderService service = new OrderServiceImpl();
    private final ShopService shopService = new ShopServiceImpl();
    private final MasterService masterService = new MasterServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        try {
            Map<Integer, List<Object>> newOrderItem = service.findAllOrderForAdmin();
            request.getSession().setAttribute(NEW_ORDER_ITEM, newOrderItem);
            List<MasterDto> allMaster = masterService.allMaster();
            request.getSession().setAttribute(MASTER_LIST, allMaster);
            router = new Router(ADMIN_ORDERS_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at GoToAdminOrderCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
