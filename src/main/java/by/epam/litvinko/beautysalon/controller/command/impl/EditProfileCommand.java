package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.PagePath;
import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;

public class EditProfileCommand implements Command {

    private static final Logger logger = LogManager.getLogger(EditProfileCommand.class);
    private static final String EMPTY_STRING = "";

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);
        String userId = request.getParameter(USER_ID);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        String birthday = request.getParameter(BIRTHDAY);
        ClientService service = new ClientServiceImpl();

        Map<String, String> mapDataForm = service.isEditFormValid(firstName, lastName, email, phone, birthday);
        if (mapDataForm.containsValue(EMPTY_STRING)) {
            request.setAttribute(WRONG_DATA_SING_UP, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_DATA_SING_UP_PATH));
            return new Router(PagePath.EDIT_PROFILE_JSP, Router.RouterType.FORWARD);
        }
        try {
            Optional<ClientDto> optionalClient = service.editData(userId, firstName, lastName, email, phone, birthday);
            if (optionalClient.isPresent()) {
                ClientDto client = optionalClient.get();
                request.getSession().setAttribute(USER, client);
                request.getSession().setAttribute(ROLE, client.role());
            }else {
                request.getSession().setAttribute(EDIT_USER_DATA_ERROR, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(EDIT_USER_DATA_ERROR_PATH));
            }
            router = new Router(PROFILE_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at EditProfileCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;

    }
}
