package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;

import java.util.List;
import java.util.Map;

public interface OrderService {

    Map<Integer, List<Object>> findOrderByMasterId(int id) throws ServiceException;
    List<OrderDto> findOrderByUserId(int id) throws ServiceException;
    Map<Integer, List<Object>> findAllOrderForAdmin() throws ServiceException;
    boolean addMasterInOrder(int orderItemId, int masterId) throws ServiceException;
    boolean updateActiveOrder(int orderId) throws ServiceException;

}
