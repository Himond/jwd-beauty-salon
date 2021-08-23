package by.epam.litvinko.beautysalon.command;

import javax.servlet.http.HttpServletRequest;

public interface Command {

    Router execute(HttpServletRequest request);

}
