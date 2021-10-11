package by.epam.litvinko.beautysalon.model.service;

import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;

import java.util.List;
import java.util.Map;

/**
 * The interface Order service.
 */
public interface OrderService {

    /**
     * Find order by master id map.
     *
     * @param id the id
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, List<Object>> findOrderByMasterId(int id) throws ServiceException;

    /**
     * Find order by user id list.
     *
     * @param id the id
     * @return the list
     * @throws ServiceException the service exception
     */
    List<OrderDto> findOrderByUserId(int id) throws ServiceException;

    /**
     * Find all order for admin map.
     *
     * @return the map
     * @throws ServiceException the service exception
     */
    Map<Integer, List<Object>> findAllOrderForAdmin() throws ServiceException;

    /**
     * Add master in order boolean.
     *
     * @param orderItemId the order item id
     * @param masterId    the master id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean addMasterInOrder(int orderItemId, int masterId) throws ServiceException;

    /**
     * Update active order boolean.
     *
     * @param orderId the order id
     * @return the boolean
     * @throws ServiceException the service exception
     */
    boolean updateActiveOrder(int orderId) throws ServiceException;

}
