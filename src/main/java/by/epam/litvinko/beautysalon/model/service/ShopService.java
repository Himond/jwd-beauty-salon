package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.util.List;
import java.util.Optional;


public interface ShopService {

    List<ProvideServicesDto> allProvideService() throws ServiceException;
    List<ProvideServicesDto> findProvideServiceByCategory(String category) throws ServiceException;
    List<Category> allCategory() throws ServiceException;
    Optional<ProvideServicesDto> findProvideServiceByID(String id) throws ServiceException;
    boolean createOrder(Cart cart, ClientDto client) throws ServiceException;

}
