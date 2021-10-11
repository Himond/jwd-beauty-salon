package by.epam.litvinko.beautysalon.controller.command;

/**
 * The type Router.
 */
public class Router {
    /**
     * The enum Router type.
     */
    public enum RouterType {
        /**
         * Forward router type.
         */
        FORWARD,
        /**
         * Redirect router type.
         */
        REDIRECT
    }

    private final String pagePath;
    private final RouterType routerType;

    /**
     * Instantiates a new Router.
     *
     * @param pagePath   the page path
     * @param routerType the router type
     */
    public Router(String pagePath, RouterType routerType) {
        this.pagePath = pagePath;
        this.routerType = routerType;
    }

    /**
     * Gets page path.
     *
     * @return the page path
     */
    public String getPagePath() {
        return pagePath;
    }

    /**
     * Gets router type.
     *
     * @return the router type
     */
    public RouterType getRouterType() {
        return routerType;
    }
}
