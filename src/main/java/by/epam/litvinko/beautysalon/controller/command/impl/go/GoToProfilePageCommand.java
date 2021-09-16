package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;
import by.epam.litvinko.beautysalon.model.service.impl.OrderServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;

public class GoToProfilePageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToProfilePageCommand.class);
    private final OrderService orderService = new OrderServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String userId = request.getParameter(USER_ID);

        try {
            List<OrderDto> orderDtoList = orderService.findOrderByUserId(Integer.parseInt(userId));
            router = new Router(PROFILE_JSP, Router.RouterType.REDIRECT);
        } catch (ServiceException e) {
            logger.error("Error at ForgetPasswordCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }

        return router;
    }
}
