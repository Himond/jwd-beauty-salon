package by.epam.litvinko.beautysalon.model.service.impl;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.exception.ServiceException;
import by.epam.litvinko.beautysalon.model.dao.EntityTransaction;
import by.epam.litvinko.beautysalon.model.dao.MasterDao;
import by.epam.litvinko.beautysalon.model.dao.impl.*;
import by.epam.litvinko.beautysalon.model.service.OrderService;
import by.epam.litvinko.beautysalon.model.service.dto.ClientDto;
import by.epam.litvinko.beautysalon.model.service.dto.MasterDto;
import by.epam.litvinko.beautysalon.model.service.dto.OrderDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.*;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);

    @Override
    public Map<Integer, List<Object>> findOrderByMasterId(int id) throws ServiceException {
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        final ProvideServiceDaoImpl productDao = new ProvideServiceDaoImpl();
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        Map<Integer, List<Object>> newOrderList = new TreeMap<>(Collections.reverseOrder());
        try {
            transaction.initTransaction(orderDao, productDao, clientDao);
            List<List<Integer>> ordersItem = orderDao.findOrderByMasterId(id);
            for (List<Integer> orderItem: ordersItem){
                List<Object> orderItemList = new ArrayList<>();
                Optional<Client> clientOptional = clientDao.findById(orderItem.get(1));
                Optional<Order> orderOptional = orderDao.findById(orderItem.get(2));
                Optional<ProvideService> productOptional = productDao.findById(orderItem.get(3));
                if(clientOptional.isPresent() && orderOptional.isPresent() && productOptional.isPresent()){
                    Client client = clientOptional.get();
                    ProvideService product = productOptional.get();
                    Order order = orderOptional.get();
                    List<ProvideService> productList =new ArrayList<>();
                    productList.add(product);
                    order.setServiceList(productList);
                    ClientDto clientDto = ClientDto.create(client);
                    OrderDto orderDto = OrderDto.create(order);
                    orderItemList.add(clientDto);
                    orderItemList.add(orderDto);
                    newOrderList.put(orderItem.get(0), orderItemList);
                }
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle find full order request at OrderService.", e);
            throw new ServiceException("Can't handle full order request at OrderService.", e);
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return newOrderList;
    }

    @Override
    public List<OrderDto> findOrderByUserId(int id) throws ServiceException {
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        final ProvideServiceDaoImpl productDao = new ProvideServiceDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        List<Order> orderList;
        List<OrderDto> orderDtoList;
        try {
            transaction.initTransaction(orderDao, productDao);
            orderList = orderDao.findOrderByClientId(id);
            for (Order order: orderList){
                order.setServiceList(productDao.findAllByOrderId(order));
            }
            transaction.commit();
            orderDtoList = orderList.stream().map(OrderDto::create).toList();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle find order by clientId request at OrderService.", e);
            throw new ServiceException("Can't handle find order by clientId request at OrderService.", e);
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return orderDtoList;
    }

    @Override
    public Map<Integer, List<Object>> findAllOrderForAdmin() throws ServiceException {
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        final ProvideServiceDaoImpl productDao = new ProvideServiceDaoImpl();
        final ClientDaoImpl clientDao = new ClientDaoImpl();
        final MasterDaoImpl masterDao = new MasterDaoImpl();
        final EntityTransaction transaction = new EntityTransaction();
        Map<Integer, List<Object>> newOrderList = new TreeMap<>(Collections.reverseOrder());
        try {
            transaction.initTransaction(orderDao, productDao, clientDao, masterDao);
            List<List<Integer>> ordersItem = orderDao.findAllOrderForAdmin();
            for (List<Integer> orderItem: ordersItem){
                List<Object> orderItemList = new ArrayList<>();
                Optional<Client> clientOptional = clientDao.findById(orderItem.get(1));
                Optional<Order> orderOptional = orderDao.findById(orderItem.get(2));
                Optional<ProvideService> productOptional = productDao.findById(orderItem.get(3));
                Optional<Master> masterOptional = masterDao.findById(orderItem.get(4));
                if(clientOptional.isPresent() && orderOptional.isPresent() && productOptional.isPresent()){
                    Client client = clientOptional.get();
                    ProvideService product = productOptional.get();
                    Order order = orderOptional.get();
                    List<ProvideService> productList =new ArrayList<>();
                    productList.add(product);
                    order.setServiceList(productList);
                    ClientDto clientDto = ClientDto.create(client);
                    OrderDto orderDto = OrderDto.create(order);
                    orderItemList.add(clientDto);
                    orderItemList.add(orderDto);
                    if (masterOptional.isPresent()){
                        Master master = masterOptional.get();
                        MasterDto masterDto = MasterDto.create(master);
                        orderItemList.add(masterDto);
                    }else {
                        orderItemList.add(null);
                    }

                    newOrderList.put(orderItem.get(0), orderItemList);
                }
            }
            transaction.commit();
        } catch (DaoException e) {
            try {
                transaction.rollback();
            } catch (DaoException ex) {
                logger.error("Unable to rollback.", e);
            }
            logger.error("Can't handle find full order request at OrderService.", e);
            throw new ServiceException("Can't handle full order request at OrderService.", e);
        }finally {
            try {
                transaction.endTransaction();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
        return newOrderList;
    }

    @Override
    public boolean addMasterInOrder(int orderItemId, int masterId) throws ServiceException {
        final EntityTransaction transaction = new EntityTransaction();
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        boolean result;
        try {
            transaction.init(orderDao);
            result = orderDao.addMasterInOrder(orderItemId, masterId);
            return result;
        } catch (DaoException e) {
            logger.error("Can't handle add master in order request at ShopService.", e);
            throw new ServiceException("Can't handle add master in order request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
    }

    @Override
    public boolean updateActiveOrder(int orderId) throws ServiceException {
        final EntityTransaction transaction = new EntityTransaction();
        final OrderDaoImpl orderDao = new OrderDaoImpl();
        boolean result;
        try {
            transaction.init(orderDao);
            result = orderDao.completedOrder(orderId);
            return result;
        } catch (DaoException e) {
            logger.error("Can't handle update active order request at ShopService.", e);
            throw new ServiceException("Can't handle update active order request at ShopService.", e);
        }finally {
            try {
                transaction.end();
            } catch (DaoException e) {
                logger.error("Error closing transaction.", e);
            }
        }
    }
}
