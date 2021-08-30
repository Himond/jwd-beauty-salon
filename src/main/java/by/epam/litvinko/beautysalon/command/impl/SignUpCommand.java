package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.*;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.dto.UserDto;
import by.epam.litvinko.beautysalon.service.impl.UserServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public class SignUpCommand implements Command {

    private static final Logger logger = LogManager.getLogger(SignUpCommand.class);

    @Override
    public Router execute(HttpServletRequest request) {
        Router router;
        String username = request.getParameter(RequestParameter.USERNAME);
        String firstName = request.getParameter(RequestParameter.FIRSTNAME);
        String lastName = request.getParameter(RequestParameter.LASTNAME);
        String email = request.getParameter(RequestParameter.EMAIL);
        String phone = request.getParameter(RequestParameter.PHONE);
        String password = request.getParameter(RequestParameter.PASSWORD);
        String passwordRep = request.getParameter(RequestParameter.PASSWORD_REP);
        UserService service = new UserServiceImpl();
        try {
            if (!password.equals(passwordRep)){
                request.getSession().setAttribute("errorPasswordSignUp", MessageManager.EN.getMessage("message.signuppassworderror"));
                return new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);
            }
            Optional<UserDto> optionalUser = service.signUp(username, firstName, lastName, email, phone, password);
            if (optionalUser.isPresent()) {
                UserDto user = optionalUser.get();
                request.getSession().setAttribute(RequestAttribute.USER, user);
                request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
                router = new Router(PagePath.MAIN_PAGE, Router.RouterType.REDIRECT);
            }else {
                request.getSession().setAttribute("errorLoginExists", MessageManager.EN.getMessage("message.signuploginerror"));
                router = new Router(PagePath.SIGN_UP_PAGE, Router.RouterType.REDIRECT);

            }
        }catch (ServiceException e) {
            logger.error("Error at SignInCommand", e);
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, Router.RouterType.REDIRECT);
        }
        return router;

    }
}
