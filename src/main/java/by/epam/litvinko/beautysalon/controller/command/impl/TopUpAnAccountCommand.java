package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.LOCALE;

public class TopUpAnAccountCommand implements Command {

    private static final Logger logger = LogManager.getLogger(TopUpAnAccountCommand.class);
    private final ClientServiceImpl service = new ClientServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        ClientDto client = (ClientDto) request.getSession().getAttribute(USER);
        String cardNumber = request.getParameter(CARD_NUMBER);
        String amount = request.getParameter(AMOUNT);
        try {
            Optional<ClientDto> clientDto = service.topUpAccount(client.clientId(), cardNumber, amount);
            if (clientDto.isPresent()){
                ClientDto currentClient = clientDto.get();
                request.getSession().setAttribute(USER, currentClient);
                request.getSession().setAttribute(ROLE, currentClient.role());
            }else {
                request.getSession().setAttribute(WRONG_DATA_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_DATA_SING_UP_PATH));
            }
            router = new Router(PROFILE_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at TopUpAnAccountCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }

}
