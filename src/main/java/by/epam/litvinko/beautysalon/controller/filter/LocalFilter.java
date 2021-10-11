package by.epam.litvinko.beautysalon.controller.filter;

import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * The type Local filter.
 */
@WebFilter(filterName = "LocalFilter", urlPatterns = {"*.jsp"})
public class LocalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String uri = httpServletRequest.getRequestURI();
        httpServletRequest.getSession().setAttribute(RequestAttribute.PREV_REQUEST, uri);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
