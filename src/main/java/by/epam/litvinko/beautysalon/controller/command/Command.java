package by.epam.litvinko.beautysalon.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface Command {

    Router execute(HttpServletRequest request) throws ServletException, IOException;

}
