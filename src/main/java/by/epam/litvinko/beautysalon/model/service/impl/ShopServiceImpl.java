package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.CategoryDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.ProvideServiceDaoImpl;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;


public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LogManager.getLogger(ShopServiceImpl.class);

    @Override
    public List<ProvideServicesDto> allProvideService() throws ServiceException {
        final ProvideServiceDaoImpl allServicesDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<ProvideService> productList;
        List<ProvideServicesDto> productListDto;
        try {
            transaction.init(allServicesDao);
            productList = allServicesDao.findAll();
            transaction.end();
            productListDto = productList.stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find all products request at ShopService.", e);
            throw new ServiceException("Can't handle find all products request at ShopService.", e);
        }
        return productListDto;
    }

    @Override
    public List<ProvideServicesDto> findProvideServiceByCategory(String category) throws ServiceException {
        final ProvideServiceDaoImpl provideServiceDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<ProvideService> productList;
        List<ProvideServicesDto> productListDto;
        try {
            transaction.init(provideServiceDao);
            productList = provideServiceDao.findAllByCategory(category);
            System.out.println(productList);
            transaction.end();
            productListDto = productList.stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find products by category request at ShopService.", e);
            throw new ServiceException("Can't handle find products by category request at ShopService.", e);
        }
        return productListDto;
    }


    @Override
    public List<Category> allCategory() throws ServiceException {
        final CategoryDaoImpl allCategoryDao = new CategoryDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<Category> categoryList;
        try {
            transaction.init(allCategoryDao);
            categoryList = allCategoryDao.findAll();
            transaction.end();
        } catch (DaoException e) {
            logger.error("Can't handle find all categories request at ShopService.", e);
            throw new ServiceException("Can't handle find all categories request at ShopService.", e);
        }
        return categoryList;
    }


}
