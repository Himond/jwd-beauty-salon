package by.epam.litvinko.beautysalon.controller.command.impl.go;

import by.epam.litvinko.beautysalon.controller.command.Command;
import by.epam.litvinko.beautysalon.controller.command.RequestParameter;
import by.epam.litvinko.beautysalon.controller.command.Router;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static by.epam.litvinko.beautysalon.controller.command.PagePath.*;
import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;


/**
 * The type Go to shop page command.
 */
public class GoToShopPageCommand implements Command {

    private static final Logger logger = LogManager.getLogger(GoToShopPageCommand.class);

    @Override
    public Router execute(HttpServletRequest request) throws ServletException, IOException {
        Router router;
        ServletContext servletContext = request.getServletContext();
        List<ProvideServicesDto> currentProductList = (List<ProvideServicesDto>) servletContext.getAttribute(PRODUCT_LIST);
        List<Category> currentCategoryList = (List<Category>) servletContext.getAttribute(CATEGORY_LIST);
        Optional<String> currentCategory = Optional.ofNullable(request.getParameter(RequestParameter.CURRENT_CATEGORY));
        if (currentCategory.isPresent()){
            String categoryId = currentCategory.get();
            List<ProvideServicesDto> productListByCategory = currentProductList.stream().filter(s-> s.categoryId() == Integer.parseInt(categoryId)).collect(Collectors.toList());
            Optional<Category> categoryOptional = currentCategoryList.stream().filter(s-> s.getId() == Integer.parseInt(categoryId)).findFirst();
            if (categoryOptional.isPresent()){
                Category category = categoryOptional.get();
                request.getSession().setAttribute(CURRENT_CATEGORY, category);
            }
            request.getSession().setAttribute(PRODUCT_LIST_BY_CATEGORY, productListByCategory);
        }else {
            request.getSession().removeAttribute(PRODUCT_LIST_BY_CATEGORY);
            request.getSession().removeAttribute(CURRENT_CATEGORY);
        }
        router = new Router(SHOP_JSP, Router.RouterType.REDIRECT);
        return router;
    }
}
