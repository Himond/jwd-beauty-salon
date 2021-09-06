package by.epam.litvinko.beautysalon.controller.command.impl;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;

import javax.servlet.http.HttpServletRequest;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.LOCALE_ATTRIBUTE;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.*;

public class EditProfileCommand implements Command {
    @Override
    public Router execute(HttpServletRequest request) {
        String local = (String) request.getSession().getAttribute(LOCALE_ATTRIBUTE);
        String firstName = request.getParameter(FIRSTNAME);
        String lastName = request.getParameter(LASTNAME);
        String email = request.getParameter(EMAIL);
        String phone = request.getParameter(PHONE);
        String birthday = request.getParameter(BIRTHDAY);
        String photo = request.getParameter(IMAGE);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(birthday);
        System.out.println(photo);
        return null;

    }
}
