package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.impl.OrderDaoImpl;
import by.epam.litvinko.beautysalon.model.dao.impl.ProvideServiceDaoImpl;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public List<OrderDto> allOrder() throws ServiceException {
        return null;
    }

    @Override
    public List<OrderDto> findOrderByUserId(int id) throws ServiceException {
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        final ProvideServiceDaoImpl productDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<Order> orderList;
        List<OrderDto> orderDtoList;
        try {
            transaction.init(orderDao);
            orderList = orderDao.findOrderByClientId(id);
            transaction.end();
            for (Order order: orderList){
                transaction.init(orderDao);
                order.setServiceList(productDao.findAllByOrderId(order));
                transaction.end();
            }
            orderDtoList = orderList.stream().map(OrderDto::create).toList();
        } catch (DaoException e) {
            logger.error("Can't handle find order by clientId request at ShopService.", e);
            throw new ServiceException("Can't handle find order by clientId request at ShopService.", e);
        }
        return orderDtoList;
    }
}
