package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.*;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.service.ClientService;
import by.epam.litvinko.beautysalon.service.MasterService;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import by.epam.litvinko.beautysalon.service.impl.ClientServiceImpl;
import by.epam.litvinko.beautysalon.service.impl.MasterServiceImpl;
import by.epam.litvinko.beautysalon.service.impl.UserServiceImpl;
import by.epam.litvinko.beautysalon.command.Router.RouterType;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;


public class SignInCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignInCommand.class);
    private final UserService userService = new UserServiceImpl();
    private final ClientService clientService = new ClientServiceImpl();
    private final MasterService masterService = new MasterServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String username = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String local = (String) request.getSession().getAttribute(RequestAttribute.LOCALE);

        try {
            Optional<UserDto> optionalUser = userService.signIn(username, password);
            if (optionalUser.isPresent()) {
                UserDto user = optionalUser.get();
                if (user.getRole() == Role.ADMINISTRATOR){
                    request.getSession().setAttribute(RequestAttribute.USER, user);
                    request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
                }else if (user.getRole() == Role.CLIENT){
                    Optional<ClientDto> optionalClient = clientService.signIn(user);
                    if (optionalClient.isPresent()){
                        ClientDto client = optionalClient.get();
                        request.getSession().setAttribute(RequestAttribute.USER, client);
                        request.getSession().setAttribute(RequestAttribute.ROLE, client.getRole());
                    }
                }else {
                    Optional<MasterDto> optionalMaster = masterService.signIn(user);
                    if (optionalMaster.isPresent()){
                        MasterDto master = optionalMaster.get();
                        request.getSession().setAttribute(RequestAttribute.USER, master);
                        request.getSession().setAttribute(RequestAttribute.ROLE, master.getRole());
                    }
                }
            }else {
                request.getSession().setAttribute(RequestAttribute.WRONG_USERNAME_OR_PASSWORD_SING_IN, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(RequestAttribute.WRONG_USERNAME_OR_PASSWORD_SING_IN_PATH));

            }
            router = new Router(PagePath.LOGIN_PAGE, RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        return router;
    }
}
