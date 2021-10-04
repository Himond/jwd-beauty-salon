package by.epam.litvinko.beautysalon.controller.listener;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.entity.Role;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.model.service.impl.ShopServiceImpl;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.List;

import static by.epam.litvinko.beautysalon.controller.command.RequestAttribute.*;

@WebListener
public class HttpSessionListenerImpl implements HttpSessionListener {

    private final ShopService service = new ShopServiceImpl();
    private List<ProvideServicesDto> productList;
    private List<Category> categoryList;
    private static final String DEFAULT_LOCALE = "ru_Ru";

    {
        try {
            productList = service.allProvideService();
            categoryList = service.allCategory();
        } catch (ServiceException e) {
            e.printStackTrace(); // FIXME: 04.10.2021 
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute(PRODUCT_LIST, productList);
        se.getSession().setAttribute(CATEGORY_LIST, categoryList);
        se.getSession().setAttribute(LOCALE, DEFAULT_LOCALE);
        se.getSession().setAttribute(CART, new Cart());
        String id = se.getSession().getId();
        se.getSession().setAttribute(SESSION_ID, id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        Role role = (Role) se.getSession().getAttribute(ROLE);
        if (role == Role.ADMINISTRATOR){
            List<ProvideServicesDto> currentProductList = (List<ProvideServicesDto>) se.getSession().getAttribute(PRODUCT_LIST);
            List<Category> currentCategoryList = (List<Category>) se.getSession().getAttribute(CATEGORY_LIST);
            if(!productList.equals(currentProductList) || !categoryList.equals(currentCategoryList)){
                updateProductList();
            }
        }
        HttpSessionListener.super.sessionDestroyed(se);
    }

    private void updateProductList(){
        try {
            productList = service.allProvideService();
            categoryList = service.allCategory();
        } catch (ServiceException e) {
            e.printStackTrace(); // FIXME: 04.10.2021 
        }
    }

}
