package by.epam.litvinko.beautysalon.controller.listener;
import by.epam.litvinko.beautysalon.entity.Cart;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

/**
 * The type Http session listener.
 */
@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    private static final String DEFAULT_LOCALE = "ru_Ru";

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(LOCALE, DEFAULT_LOCALE);
        se.getSession().setAttribute(CART, new Cart());
        String id = se.getSession().getId();
        se.getSession().setAttribute(SESSION_ID, id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSessionListener.super.sessionDestroyed(se);
    }


}
