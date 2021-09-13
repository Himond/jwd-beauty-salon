package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.RequestParameter;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

public class CreateOrderCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CreateOrderCommand.class);
    private final ShopService service = new ShopServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        final HttpSession session = request.getSession();
        ClientDto client = (ClientDto) request.getSession().getAttribute(USER);
        Cart cart = (Cart) request.getSession().getAttribute(CART);
        String local = (String) request.getSession().getAttribute(LOCALE);
        try {
            if(service.createOrder(cart, client)){
                session.setAttribute(ORDER_ID, cart.getOrderId());
                cart.clearCart();
                session.setAttribute(CART, cart);
                router = new Router(CREATE_ORDER_JSP, Router.RouterType.REDIRECT);
            }else {
                request.getSession().setAttribute(PRODUCT_NOT_FOUND, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(PRODUCT_NOT_FOUND_PATH));
                router = new Router(CART_DETAIL_JSP, Router.RouterType.REDIRECT);
            }
        }catch (ServiceException e) {
            logger.error("Error at CreateOrderCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }

}
