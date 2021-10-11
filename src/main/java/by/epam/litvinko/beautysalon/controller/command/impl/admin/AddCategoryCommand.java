package by.epam.litvinko.beautysalon.controller.command.impl.admin;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.manager.MessageManager;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;
import static by.epam.litvinko.beautysalon.controller.command.RequestParameter.NEW_CATEGORY_NAME;


/**
 * The type Add category command.
 */
public class AddCategoryCommand implements Command {

    private static final Logger logger = LogManager.getLogger(AddCategoryCommand.class);
    private final ShopService service = new ShopServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        String local = (String) request.getSession().getAttribute(LOCALE);
        String categoryName = request.getParameter(NEW_CATEGORY_NAME);
        Category category = new Category();
        category.setName(categoryName);
        try {
            if (service.createCategory(category)) {
                ServletContext servletContext = request.getServletContext();
                List<Category> categoryList = service.allCategory();
                servletContext.setAttribute(CATEGORY_LIST, categoryList);
            }else {
                request.getSession().setAttribute(WRONG_CATEGORY, MessageManager.valueOf(local.toUpperCase(Locale.ROOT)).getMessage(WRONG_CATEGORY_PATH));
            }
            router = new Router(ADMIN_JSP, Router.RouterType.REDIRECT);
        }catch (ServiceException e) {
            logger.error("Error at AddCategoryCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
