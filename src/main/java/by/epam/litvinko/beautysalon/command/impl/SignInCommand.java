package by.epam.litvinko.beautysalon.command.impl;

import by.epam.litvinko.beautysalon.command.*;
import by.epam.litvinko.beautysalon.entity.User;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.service.UserService;
import by.epam.litvinko.beautysalon.service.impl.UserServiceImpl;
import by.epam.litvinko.beautysalon.command.Router.RouterType;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;


public class SignInCommand implements Command {

    @Override
    public Router execute(HttpServletRequest request) {
        System.out.println("====> SignIn Command");
        Router router;
        String username = request.getParameter(RequestParameter.USERNAME);
        String password = request.getParameter(RequestParameter.PASSWORD);
        System.out.println(username);
        System.out.println(password);
        UserService service = new UserServiceImpl();
        System.out.println("before try");
        try {
            System.out.println("inside try");
            Optional<User> optionalUser = service.signIn(username, password);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                request.getSession().setAttribute(RequestAttribute.USER, user);
                request.getSession().setAttribute(RequestAttribute.ROLE, user.getRole());
                router = new Router(PagePath.MAIN_PAGE, RouterType.REDIRECT);
            }else {
                request.getSession().setAttribute(RequestAttribute.WRONG_DATA, true);
                router = new Router(PagePath.LOGIN_PAGE, RouterType.REDIRECT);
            }
        }catch (ServiceException e) {
            request.setAttribute(RequestAttribute.EXCEPTION, e);
            router = new Router(PagePath.ERROR_PAGE, RouterType.REDIRECT);
        }
        System.out.println("Exit");
        return router;
    }
}
