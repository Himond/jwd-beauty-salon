package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.util.List;
import java.util.Optional;


/**
 * The interface Shop service.
 */
public interface ShopService {

    /**
     * All provide service list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProvideServicesDto> allProvideService() throws ServiceException;

    /**
     * Find provide service by category list.
     *
     * @param category the category
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProvideServicesDto> findProvideServiceByCategory(String category) throws ServiceException;

    /**
     * All category list.
     *
     * @return the list
     * @throws ServiceException the service exception
     */
    List<Category> allCategory() throws ServiceException;

    /**
     * Find review by service id list.
     *
     * @param id the id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<ProvideServiceReview> findReviewByServiceId(int id) throws ServiceException;

    /**
     * Find provide service by id optional.
     *
     * @param id the id
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ProvideServicesDto> findProvideServiceByID(String id) throws ServiceException;

    /**
     * Create order optional.
     *
     * @param cart   the cart
     * @param client the client
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<ClientDto> createOrder(Cart cart, ClientDto client) throws ServiceException;

    /**
     * Create category boolean.
     *
     * @param category the category
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createCategory(Category category) throws ServiceException;

    /**
     * Create product boolean.
     *
     * @param service the service
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createProduct(ProvideService service) throws ServiceException;

    /**
     * Create coupon boolean.
     *
     * @param code     the code
     * @param discount the discount
     * @param validTo  the valid to
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean createCoupon(String code, String discount, String validTo) throws ServiceException;

    /**
     * Add review boolean.
     *
     * @param clientId   the client id
     * @param productId  the product id
     * @param reviewBody the review body
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addReview(int clientId, int productId, String reviewBody) throws ServiceException;

    /**
     * Find coupon by code optional.
     *
     * @param code the code
     * @return the optional
     * @throws ServiceException the service exception
     */
    Optional<Coupon> findCouponByCode(String code) throws ServiceException;

}
