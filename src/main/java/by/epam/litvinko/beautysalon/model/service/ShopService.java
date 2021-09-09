package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;

import java.util.List;


public interface ShopService {

    List<ProvideServicesDto> allProvideService() throws ServiceException;
    List<ProvideServicesDto> findProvideServiceByCategory(String category) throws ServiceException;
    List<Category> allCategory() throws ServiceException;

}
