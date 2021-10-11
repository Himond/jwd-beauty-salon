package by.epam.litvinko.beautysalon.controller.filter;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import by.epam.litvinko.beautysalon.controller.command.RequestAttribute;
import by.epam.litvinko.beautysalon.entity.Role;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.EnumMap;
import java.util.List;


/**
 * The type Page redirect security filter.
 */
@WebFilter( urlPatterns = {"/jsp/*"},
initParams = {@WebInitParam(name = "INDEX_PATH", value = "/index.jsp") })
public class PageRedirectSecurityFilter implements Filter {

    private String indexPath;
    private EnumMap<Role, List<String>> availablePages;
    private List<String> adminPages;
    private List<String> clientPages;
    private List<String> masterPages;
    private List<String> guestPages;
    private static final String INDEX_PATH = "INDEX_PATH";

    public void init(FilterConfig config) throws ServletException {
        indexPath = config.getInitParameter(INDEX_PATH);
        availablePages = new EnumMap<>(Role.class);

        guestPages = List.of(ERROR_404_JSP, ERROR_JSP, LOGIN_JSP,
                    MAIN_JSP, SHOP_JSP, PRODUCT_DETAIL_JSP, CART_DETAIL_JSP, CONTACTS_JSP, SIGNUP_JSP, FORGOT_PASSWORD_JSP);

        adminPages = List.of(ERROR_404_JSP, ERROR_JSP, LOGIN_JSP, MAIN_JSP, SHOP_JSP, PRODUCT_DETAIL_JSP, CART_DETAIL_JSP, CONTACTS_JSP, SIGNUP_JSP,
                ADMIN_JSP, FORGOT_PASSWORD_JSP, ADMIN_ORDERS_JSP);

        clientPages = List.of(ERROR_404_JSP, ERROR_JSP, LOGIN_JSP, MAIN_JSP, SHOP_JSP, PRODUCT_DETAIL_JSP, CART_DETAIL_JSP, CONTACTS_JSP, SIGNUP_JSP,
                CREATE_ORDER_JSP, TOP_UP_AN_ACCOUNT_JSP, PROFILE_JSP, FORGOT_PASSWORD_JSP, EDIT_PROFILE_JSP, EDIT_PASSWORD_JSP, CREATE_ORDER_JSP);


        masterPages = List.of(ERROR_404_JSP, ERROR_JSP, LOGIN_JSP, MAIN_JSP, SHOP_JSP, PRODUCT_DETAIL_JSP, CART_DETAIL_JSP, CONTACTS_JSP, SIGNUP_JSP,
                 PROFILE_JSP, FORGOT_PASSWORD_JSP, EDIT_PROFILE_JSP, CREATE_ORDER_JSP, MASTER_ORDERS_JSP);

        availablePages.put(Role.GUEST, guestPages);
        availablePages.put(Role.ADMINISTRATOR, adminPages);
        availablePages.put(Role.MASTER, masterPages);
        availablePages.put(Role.CLIENT, clientPages);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String servletPath = httpServletRequest.getServletPath().replaceFirst("/", "");
        HttpSession session = httpServletRequest.getSession();
        Role role = (Role) session.getAttribute(RequestAttribute.ROLE);

        if (role == null) {
            role = Role.GUEST;
        }

        if (role.equals(Role.ADMINISTRATOR) && !adminPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(indexPath).forward(request, response);
        }

        if (role.equals(Role.CLIENT) && !clientPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(indexPath).forward(request, response);
        }

        if (role.equals(Role.MASTER) && !masterPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(indexPath).forward(request, response);
        }

        if (role.equals(Role.GUEST) && !guestPages.contains(servletPath)) {
            httpServletRequest.getRequestDispatcher(indexPath).forward(request, response);
        }
        chain.doFilter(request, response);

    }

    public void destroy() {
    }

}
