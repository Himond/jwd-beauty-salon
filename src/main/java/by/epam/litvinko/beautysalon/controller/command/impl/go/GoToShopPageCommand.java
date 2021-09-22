package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.RequestParameter;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.EXCEPTION;

public class GoToShopPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToShopPageCommand.class);
    private final ShopService service = new ShopServiceImpl();

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        try {
            List<ProvideServicesDto> productList;
            List<Category> categoryList = service.allCategory();
            Optional<String> currentCategory = Optional.ofNullable(request.getParameter(RequestParameter.CURRENT_CATEGORY));
            if (currentCategory.isPresent()){
                String category = currentCategory.get();
                productList = service.findProvideServiceByCategory(category);
                request.getSession().setAttribute(CURRENT_CATEGORY, category);
            }else {
                productList = service.allProvideService();
                request.getSession().removeAttribute(CURRENT_CATEGORY);
            }
            request.getSession().setAttribute(PRODUCT_LIST, productList);
            request.getSession().setAttribute(CATEGORY_LIST, categoryList);
            router = new Router(SHOP_JSP, Router.RouterType.FORWARD);
        }catch (ServiceException e) {
            logger.error("Error at GoToShopPageCommand", e);
            request.setAttribute(EXCEPTION, e);
            router = new Router(ERROR_JSP, Router.RouterType.REDIRECT);
        }
        return router;
    }
}
