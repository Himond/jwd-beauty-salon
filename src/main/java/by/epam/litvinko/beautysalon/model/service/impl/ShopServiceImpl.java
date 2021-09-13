package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.CategoryDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.OrderDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.ProvideServiceDaoImpl;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.util.MailSender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


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

    @Override
    public Optional<ProvideServicesDto> findProvideServiceByID(String id) throws ServiceException {
        final ProvideServiceDaoImpl provideServiceDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        Optional<ProvideService> productOptional;
        try {
            transaction.init(provideServiceDao);
            productOptional = provideServiceDao.findById(Integer.parseInt(id));
            transaction.end();
            if (productOptional.isPresent()){
                ProvideService product = productOptional.get();
                ProvideServicesDto productDto = ProvideServicesDto.create(product);
                return Optional.of(productDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle find product by id request at ShopService.", e);
            throw new ServiceException("Can't handle find product by id request at ShopService.", e);
        }
        return Optional.empty();
    }

    @Override
    public boolean createOrder(Cart cart, ClientDto client) throws ServiceException {
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();

        boolean result = false;
        Order order;
        try {
            transaction.initTransaction(orderDao);
            order = Order.newBuilder()
                    .setIsPaid(true)
                    .setClientId(client.clientId())
                    .setCouponId(cart.getCoupon() != null ? cart.getCoupon().getId() : 0)
                    .setCreated(LocalDate.now())
                    .build();
            orderDao.create(order);
            if (order.getId() != 0){
                cart.setOrderId(order.getId());
                result = orderDao.createOrderItem(cart);
            }
            transaction.commit();
            MailSender.send(client.email(), MailSender.messageOrderSuccessful(client.firstName(), order.getId()));
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't create order request at ShopService.", e);
            throw new ServiceException("Can't create order request at ShopService.", e);
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return result;
    }


}