package by.epam.litvinko.beautysalon.controller;

import by.epam.litvinko.beautysalon.controller.command.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The type Controller.
 */
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

    /**
     * Process request.
     *
     * @param request  the request
     * @param response the response
     * @throws IOException      the io exception
     * @throws ServletException the servlet exception
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String commandName = request.getParameter(RequestParameter.COMMAND);
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
