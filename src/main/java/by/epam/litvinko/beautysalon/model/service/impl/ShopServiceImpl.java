package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.*;
import by.epam.litvinko.beautysalon.model.service.ShopService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import by.epam.litvinko.beautysalon.model.validator.SalonValidator;
import by.epam.litvinko.beautysalon.model.validator.impl.SalonValidatorImpl;
import by.epam.litvinko.beautysalon.util.MailSender;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;
import static by.epam.litvinko.beautysalon.model.dao.ColumnName.COUPON_IS_ACTIVE;


public class ShopServiceImpl implements ShopService {

    private static final Logger logger = LogManager.getLogger(ShopServiceImpl.class);
    private final SalonValidator validator = SalonValidatorImpl.getInstance();

    @Override
    public List<ProvideServicesDto> allProvideService() throws ServiceException {
        final ProvideServiceDaoImpl allServicesDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<ProvideService> productList;
        List<ProvideServicesDto> productListDto;
        try {
            transaction.init(allServicesDao);
            productList = allServicesDao.findAll();
            productListDto = productList.stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find all products request at ShopService.", e);
            throw new ServiceException("Can't handle find all products request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
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
            productListDto = productList.stream().map(s -> new ProvideServicesDto(s.getId(), s.getCategoryId(), s.getName(), s.getDescription(), s.getPrice(), s.getServiceTime(), s.getImage())).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find products by category request at ShopService.", e);
            throw new ServiceException("Can't handle find products by category request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
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
        } catch (DaoException e) {
            logger.error("Can't handle find all categories request at ShopService.", e);
            throw new ServiceException("Can't handle find all categories request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return categoryList;
    }

    @Override
    public List<ProvideServiceReview> findReviewByServiceId(int id) throws ServiceException {
        final ProvideServiceReviewDaoImpl reviewDao = new ProvideServiceReviewDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<ProvideServiceReview> serviceReviews;
        try {
            transaction.init(reviewDao);
            serviceReviews = reviewDao.findAllByServiceId(id);

        } catch (DaoException e) {
            logger.error("Can't handle find all categories request at ShopService.", e);
            throw new ServiceException("Can't handle find all categories request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return serviceReviews;
    }

    @Override
    public Optional<ProvideServicesDto> findProvideServiceByID(String id) throws ServiceException {
        final ProvideServiceDaoImpl provideServiceDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        Optional<ProvideService> productOptional;
        try {
            transaction.init(provideServiceDao);
            productOptional = provideServiceDao.findById(Integer.parseInt(id));
            if (productOptional.isPresent()){
                ProvideService product = productOptional.get();
                ProvideServicesDto productDto = ProvideServicesDto.create(product);
                return Optional.of(productDto);
            }
        } catch (DaoException e) {
            logger.error("Can't handle find product by id request at ShopService.", e);
            throw new ServiceException("Can't handle find product by id request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
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

    @Override
    public boolean createCategory(Category category) throws ServiceException {
        final EntityTransaction transaction = new EntityTransaction();
        final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
        boolean result;
        try {
            transaction.init(categoryDao);
            result = categoryDao.create(category);
            return result;
        } catch (DaoException e) {
            logger.error("Can't handle create category for product request at ShopService.", e);
            throw new ServiceException("Can't handle create category for product request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
    }

    @Override
    public boolean createCoupon(String code, String discount, String validTo) throws ServiceException {
        final EntityTransaction transaction = new EntityTransaction();
        final CouponDaoImpl couponDao = new CouponDaoImpl();
        boolean result;

        Coupon coupon;
        Coupon.Builder builder = Coupon.newBuilder();
        builder.setCode(code)
                .setValidFrom(LocalDate.now())
                .setValidTo(LocalDate.parse(validTo))
                .setDiscount(Integer.parseInt(discount));
        coupon = builder.build();
        try {
            transaction.init(couponDao);
            result = couponDao.create(coupon);
            return result;
        } catch (DaoException e) {
            logger.error("Can't handle create coupon for product request at ShopService.", e);
            throw new ServiceException("Can't handle create coupon for product request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
    }

    @Override
    public boolean addReview(int clientId, int productId, String reviewBody) throws ServiceException {
        final EntityTransaction transaction = new EntityTransaction();
        final ProvideServiceReviewDaoImpl reviewDao = new ProvideServiceReviewDaoImpl();
        boolean result = false;
        if (validator.validateReview(reviewBody)){
            try {
                transaction.init(reviewDao);
                ProvideServiceReview review = new ProvideServiceReview();
                review.setServiceId(productId);
                review.setClientId(clientId);
                review.setReview(reviewBody);
                result = reviewDao.create(review);
                return result;
            } catch (DaoException e) {
                logger.error("Can't handle create review for product request at ShopService.", e);
                throw new ServiceException("Can't handle create review for product request at ShopService.", e);
            }finally {
                try {
                    transaction.end();
                } catch (DaoException e) {
                    logger.error("Error closing transaction.", e);
                }
            }
        }
        return result;
    }

    @Override
    public Optional<Coupon> findCouponByCode(String code) throws ServiceException {
        final CouponDaoImpl couponDao = new CouponDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        Optional<Coupon> coupon;
        if (validator.validateCoupon(code)){
            try {
                transaction.init(couponDao);
                coupon = couponDao.findByCode(code);
                return coupon;
            } catch (DaoException e) {
                logger.error("Can't handle find product by id request at ShopService.", e);
                throw new ServiceException("Can't handle find product by id request at ShopService.", e);
            }finally {
                try {
                    transaction.end();
                } catch (DaoException e) {
                    logger.error("Error closing transaction.", e);
                }
            }
        }
        return Optional.empty();
    }


}
