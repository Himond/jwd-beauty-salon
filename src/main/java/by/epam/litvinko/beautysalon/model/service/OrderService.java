package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;

import java.util.List;

public interface OrderService {

    List<OrderDto> allOrder() throws ServiceException;
    List<OrderDto> findOrderByUserId(int id) throws ServiceException;

}
