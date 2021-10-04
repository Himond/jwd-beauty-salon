package by.epam.litvinko.beautysalon.controller;

import by.epam.litvinko.beautysalon.controller.command.*;
import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;


@WebServlet(name = "Controller", urlPatterns = {"/controller", "*.do"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 25)
public class Controller extends HttpServlet {

    private final CommandProvider COMMAND_PROVIDER = CommandProvider.getInstance();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
        System.out.println(commandName);
        Command command = COMMAND_PROVIDER.getCommand(commandName);
        Router router = command.execute(request);
        switch (router.getRouterType()) {
            case REDIRECT -> response.sendRedirect(router.getPagePath());
            case FORWARD -> {
                RequestDispatcher dispatcher = request.getRequestDispatcher(router.getPagePath());
                dispatcher.forward(request, response);
            }
            default -> response.sendRedirect(PagePath.ERROR_404_JSP);
        }
    }


}
