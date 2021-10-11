package by.epam.litvinko.beautysalon.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The interface Command.
 */
public interface Command {

    /**
     * Execute router.
     *
     * @param request the request
     * @return the router
     * @throws ServletException the servlet exception
     * @throws IOException      the io exception
     */
    Router execute(HttpServletRequest request) throws ServletException, IOException;

}
