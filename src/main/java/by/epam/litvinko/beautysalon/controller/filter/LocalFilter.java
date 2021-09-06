package by.epam.litvinko.beautysalon.controller.filter;

import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "LocalFilter", urlPatterns = {"*.jsp"})
public class LocalFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)request;
        String contextPath = httpServletRequest.getContextPath();
        int len = contextPath.length();
        String uri = httpServletRequest.getRequestURI();
        String path = uri.substring(len);
        httpServletRequest.getSession().setAttribute(RequestAttribute.PREV_REQUEST, uri);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

}
