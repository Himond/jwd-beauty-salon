package by.epam.litvinko.beautysalon.controller.listener;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.connection.DatabaseConnectionPool;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.CATEGORY_LIST;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.PRODUCT_LIST;


/**
 * The type Servlet context listener.
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ServletContextListenerImpl.class);
    private final ShopService service = new ShopServiceImpl();
    private List<ProvideServicesDto> productList;
    private List<Category> categoryList;


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DatabaseConnectionPool.getInstance();
        try {
            productList = service.allProvideService();
            categoryList = service.allCategory();
        } catch (ServiceException e) {
            logger.error("Error loading the list of services and categories: ", e);
            throw new RuntimeException("Error loading the list of services and categories: ", e);
        }
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(PRODUCT_LIST, productList);
        servletContext.setAttribute(CATEGORY_LIST, categoryList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        DatabaseConnectionPool.getInstance().destroyPool();
    }
}