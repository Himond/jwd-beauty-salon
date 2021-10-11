package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.*;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ClientService;
import by.epam.litvinko.beautysalon.model.service.MasterService;
import by.epam.litvinko.beautysalon.model.service.UserService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.UserDto;
import by.epam.litvinko.beautysalon.model.service.impl.ClientServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.MasterServiceImpl;
import by.epam.litvinko.beautysalon.model.service.impl.UserServiceImpl;
import by.epam.litvinko.beautysalon.controller.command.Router.RouterType;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;
import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;


/**
 * The type Sign in command.
 */
public class SignInCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private final UserService userService = new UserServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final MasterService masterService = new MasterServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String username = request.getParameter(USERNAME);
        String password = request.getParameter(PASSWORD);
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        try {
            Optional<UserDto> optionalUser = userService.signIn(username, password);
            if (optionalUser.isPresent()) {
                UserDto user = optionalUser.get();
                if (user.role() == Role.ADMINISTRATOR){
                    request.getSession().setAttribute(USER, user);
                    request.getSession().setAttribute(ROLE, user.role());
                }else if (user.role() == Role.CLIENT){
                    Optional<ClientDto> optionalClient = clientService.signIn(user);
                    if (optionalClient.isPresent()){
                        ClientDto client = optionalClient.get();
                        request.getSession().setAttribute(USER, client);
                        request.getSession().setAttribute(ROLE, client.role());
                    }
                }else {
                    Optional<MasterDto> optionalMaster = masterService.signIn(user);
                    if (optionalMaster.isPresent()){
                        MasterDto master = optionalMaster.get();
                        request.getSession().setAttribute(USER, master);
                        request.getSession().setAttribute(ROLE, master.role());
                    }
                }
            }else {
                request.getSession().setAttribute(WRONG_USERNAME_OR_PASSWORD_SING_IN, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_USERNAME_OR_PASSWORD_SING_IN_PATH));

            }

            router = new Router(LOGIN_JSP, RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, RouterType.REDIRECT);
        }
        return router;
    }
}
