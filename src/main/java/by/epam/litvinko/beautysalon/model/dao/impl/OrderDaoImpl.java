package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.exception.DaoException;
import by.epam.litvinko.beautysalon.model.dao.OrderDao;
import by.epam.litvinko.beautysalon.model.service.dto.ProvideServicesDto;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;


import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

/**
 * The type Order dao.
 */
public class OrderDaoImpl extends AbstractDao<Integer, Order> implements OrderDao {

    private static Logger logger = LogManager.getLogger(OrderDaoImpl.class);

    private static final String SELECT_ALL_ORDER = "SELECT id, client_id, coupon_id, created, " +
            "is_paid, is_active " +
            "FROM salon_order;";

    private static final String SELECT_ALL_ORDERS_BY_CLIENT_ID = "SELECT id, client_id, coupon_id, created, " +
            "is_paid, is_active " +
            "FROM salon_order " +
            "WHERE client_id = ?;";

    private static final String SELECT_ALL_ORDERS_BY_MASTER_ID = "SELECT order_item.id, order_item.order_id, order_item.service_id, order_item.master_id, " +
            "salon_order.client_id " +
            "FROM order_item " +
            "JOIN salon_order ON order_item.order_id = salon_order.id " +
            "WHERE order_item.master_id = ?;";

    private static final String SELECT_ALL_ORDERS_FOR_ADMIN = "SELECT order_item.id, order_item.order_id, order_item.service_id, order_item.master_id, " +
            "salon_order.client_id " +
            "FROM order_item " +
            "JOIN salon_order ON order_item.order_id = salon_order.id ;";

    private static final String SELECT_ORDER_BY_ID = "SELECT id, client_id, coupon_id, created, " +
            "is_paid, is_active " +
            "FROM salon_order " +
            "WHERE id = ?;";

    private static final String INSERT_ORDER = "INSERT INTO salon_order(client_id, coupon_id, created, is_paid) " +
            "VALUES ( ?, ?, ?, ?)";

    private static final String INSERT_ORDER_WITHOUT_COUPON = "INSERT INTO salon_order(client_id, created, is_paid) " +
            "VALUES ( ?, ?, ?)";

    private static final String DELETE_ORDER_BY_CLIENT_ID = "DELETE FROM salon_order WHERE client_id = ?;";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM salon_order WHERE id = ?;";

    private static final String UPDATE_ORDER_BY_ID = "UPDATE salon_order SET client_id = ?, " +
            "coupon_id = ?, created = ?, is_paid = ?, is_active = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_ACTIVE_ORDER_BY_ID = "UPDATE salon_order SET is_active = ? " +
            "WHERE id = ?;";

    private static final String INSERT_MASTER_IN_ORDER_ITEM = "UPDATE order_item SET master_id = ? " +
            "WHERE id = ?;";

    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_item(order_id, service_id) " +
            "VALUES ( ?, ?)";

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDER)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Order order = buildOrder(resultSet);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return orderList;
    }

    @Override
    public Optional<Order> findById(Integer id) throws DaoException {
        Order order = null;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_ORDER_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    order = buildOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(order);
    }

    @Override
    public boolean create(Order entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        if(entity.getCouponId() == 0){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_WITHOUT_COUPON,  Statement.RETURN_GENERATED_KEYS)){
                statement.setInt(1, entity.getClientId());
                statement.setDate(2, Date.valueOf(entity.getCreated()));
                statement.setBoolean(3, entity.isPaid());
                result = statement.executeUpdate() == 1;
                ResultSet generatedKeys = statement.getGeneratedKeys();
                int orderId;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    entity.setId(orderId);
                } else {
                    throw new SQLException("Can not make new order.");
                }
            } catch (SQLException e) {
                logger.error("Prepare statement cannot be retrieved from the connection.", e);
                throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
            }
        }else {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER,  Statement.RETURN_GENERATED_KEYS)){
                statement.setInt(1, entity.getClientId());
                statement.setInt(2, entity.getCouponId());
                statement.setDate(3, Date.valueOf(entity.getCreated()));
                statement.setBoolean(4, entity.isPaid());
                result = statement.executeUpdate() == 1;
                ResultSet generatedKeys = statement.getGeneratedKeys();
                int orderId;
                if (generatedKeys.next()) {
                    orderId = generatedKeys.getInt(1);
                    entity.setId(orderId);
                } else {
                    throw new SQLException("Can not make new order.");
                }
            } catch (SQLException e) {
                logger.error("Prepare statement cannot be retrieved from the connection.", e);
                throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
            }
        }
        return  result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(DELETE_ORDER_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Order> update(Order entity) throws DaoException {
        Optional<Order> order;
        Connection connection = super.connection;
        order = findById(entity.getId());
        if (order.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_BY_ID)){
                statement.setInt(1, entity.getClientId());
                statement.setInt(2, entity.getCouponId());
                statement.setDate(3, Date.valueOf(entity.getCreated()));
                statement.setBoolean(4,entity.isPaid());
                statement.setBoolean(5,entity.isActive());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e);
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e);
            }
        } else {
            logger.info("Order id = " + entity.getId() + " don't exist.");
        }
        order = findById(entity.getId());
        return order;
    }

    @Override
    public boolean createOrderItem(Cart cart) throws DaoException {
        boolean result = false;
        Connection connection = super.connection;
        for(ProvideServicesDto service: cart.getServices()){
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_ITEM)){
                statement.setInt(1, cart.getOrderId());
                statement.setInt(2, service.id());
                result = statement.executeUpdate() == 1;
            } catch (SQLException e) {
                logger.error("Prepare statement cannot be retrieved from the connection.", e);
                throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
            }
        }
        return  result;
    }

    @Override
    public List<Order> findOrderByClientId(int id) throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_CLIENT_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Order order = buildOrder(resultSet);
                    orderList.add(order);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return orderList;
    }

    @Override
    public List<List<Integer>> findOrderByMasterId(int id) throws DaoException {
        List<List<Integer>> orderItemList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_MASTER_ID)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    List<Integer> idList = new ArrayList<>();
                    idList.add(resultSet.getInt(ORDER_ITEM_ID));
                    idList.add(resultSet.getInt(ORDER_CLIENT_ID));
                    idList.add(resultSet.getInt(ORDER_ITEM_ORDER_ID));
                    idList.add(resultSet.getInt(ORDER_ITEM_SERVICE_ID));
                    orderItemList.add(idList);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return orderItemList;
    }

    @Override
    public List<List<Integer>> findAllOrderForAdmin() throws DaoException {
        List<List<Integer>> orderItemList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_ORDERS_FOR_ADMIN)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    List<Integer> idList = new ArrayList<>();
                    idList.add(resultSet.getInt(ORDER_ITEM_ID));
                    idList.add(resultSet.getInt(ORDER_CLIENT_ID));
                    idList.add(resultSet.getInt(ORDER_ITEM_ORDER_ID));
                    idList.add(resultSet.getInt(ORDER_ITEM_SERVICE_ID));
                    idList.add(resultSet.getInt(ORDER_ITEM_MASTER_ID));
                    orderItemList.add(idList);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return orderItemList;

    }

    @Override
    public boolean addMasterInOrder(int orderItemId, int masterId) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(INSERT_MASTER_IN_ORDER_ITEM)) {
            statement.setInt(1, masterId);
            statement.setInt(2, orderItemId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public boolean completedOrder(int orderId) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(UPDATE_ACTIVE_ORDER_BY_ID)) {
            statement.setBoolean(1, false);
            statement.setInt(2, orderId);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order;
        Order.Builder builder = Order.newBuilder();
        builder.setId(resultSet.getInt(ORDER_ID))
                .setClientId(resultSet.getInt(ORDER_CLIENT_ID))
                .setCouponId(resultSet.getInt(ORDER_COUPON_ID))
                .setCreated(LocalDate.parse(resultSet.getString(ORDER_CREATED)))
                .setIsPaid(resultSet.getBoolean(ORDER_IS_PAID))
                .setIsActive(resultSet.getBoolean(ORDER_IS_ACTIVE));
        order = builder.build();
        return order;
    }


}
