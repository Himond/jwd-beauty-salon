package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.RequestParameter;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.ProvideServiceReview;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

public class AddReviewCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddReviewCommand.class);
    private final ShopService service = new ShopServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        ClientDto client = (ClientDto) request.getSession().getAttribute(USER);
        String productId = request.getParameter(RequestParameter.CURRENT_PRODUCT_ID);
        String reviewBody = request.getParameter(RequestParameter.REVIEW_BODY);
        try {
            if (!service.addReview(client.clientId(), Integer.parseInt(productId), reviewBody)){
                request.getSession().setAttribute(WRONG_DATA_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_DATA_SING_UP_PATH));
            }
            List<ProvideServiceReview> reviews = service.findReviewByServiceId(Integer.parseInt(productId));
            request.getSession().setAttribute(CURRENT_REVIEW_LIST, reviews);
            router = new Router(PRODUCT_DETAIL_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at AddReviewCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;

    }
}
