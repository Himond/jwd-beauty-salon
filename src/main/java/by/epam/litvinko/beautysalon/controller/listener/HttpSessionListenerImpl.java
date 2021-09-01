package by.epam.litvinko.beautysalon.controller.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {


    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("locale", "ru_Ru");
        String id = se.getSession().getId();
        se.getSession().setAttribute("session_id", id);
    }

}
