package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.util.List;
import java.util.Optional;


public interface ShopService {

    List<ProvideServicesDto> allProvideService() throws ServiceException;
    List<ProvideServicesDto> findProvideServiceByCategory(String category) throws ServiceException;
    List<Category> allCategory() throws ServiceException;
    List<ProvideServiceReview> findReviewByServiceId(int id) throws ServiceException;
    Optional<ProvideServicesDto> findProvideServiceByID(String id) throws ServiceException;
    Optional<ClientDto> createOrder(Cart cart, ClientDto client) throws ServiceException;
    boolean createCategory(Category category) throws ServiceException;
    boolean createCoupon(String code, String discount, String validTo) throws ServiceException;
    boolean addReview(int clientId, int productId, String reviewBody) throws ServiceException;
    Optional<Coupon> findCouponByCode(String code) throws ServiceException;

}
