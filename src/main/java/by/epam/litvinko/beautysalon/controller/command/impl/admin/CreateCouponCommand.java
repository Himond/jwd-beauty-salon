package by.epam.litvinko.beautysalon.controller.command.impl.admin;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.ADMIN_JSP;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.ERROR_JSP;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.LOCALE;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;

/**
 * The type Create coupon command.
 */
public class CreateCouponCommand implements Command {

    private static final Logger logger = LogManager.getLogger(CreateCouponCommand.class);
    private final ShopService service = new ShopServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        String couponCode= request.getParameter(COUPON_CODE);
        String dateValidTo = request.getParameter(COUPON_VALID_TO);
        String discount = request.getParameter(COUPON_DISCOUNT);
        try {
            if (!service.createCoupon(couponCode, discount, dateValidTo)) {
                request.getSession().setAttribute(WRONG_COUPON, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_COUPON_PATH));
            }
            router = new Router(ADMIN_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at CreateOrderCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }


}
