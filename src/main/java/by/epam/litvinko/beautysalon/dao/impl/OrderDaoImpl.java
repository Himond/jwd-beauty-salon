package by.epam.litvinko.beautysalon.dao.impl;

import by.epam.litvinko.beautysalon.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Order;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.dao.ColumnName.*;

public class OrderDaoImpl extends AbstractDao<Integer, Order> {

    private static Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_ORDER = "SELECT id, client_id, coupon_id, created, " +
            "date_of_service, is_paid, is_active " +
            "FROM salon_order;";

    private static final String SELECT_ORDER_BY_CLIENT_ID = "SELECT id, client_id, coupon_id, created, " +
            "date_of_service, is_paid, is_active, " +
            "FROM salon_order " +
            "WHERE client_id = ?;";

    private static final String SELECT_ORDER_BY_ID = "SELECT id, client_id, coupon_id, created, " +
            "date_of_service, is_paid, is_active, " +
            "FROM salon_order " +
            "WHERE id = ?;";

    private static final String INSERT_ORDER = "INSERT INTO salon_order(client_id, coupon_id, created, " +
            "date_of_service) " +
            "VALUES (?, ?, ?, ?)";

    private static final String DELETE_ORDER_BY_CLIENT_ID = "DELETE FROM salon_order WHERE client_id = ?;";

    private static final String DELETE_ORDER_BY_ID = "DELETE FROM salon_order WHERE id = ?;";

    private static final String UPDATE_ORDER_BY_ID = "UPDATE salon_order SET client_id = ?, " +
            "coupon_id = ?, created = ?, date_of_service = ?, is_paid = ?, is_active = ? " +
            "WHERE id = ?;";

    private static final String UPDATE_ORDER_IS_PAID = "UPDATE salon_order SET is_active = ? " +
            "WHERE id = ?;";

    // FIXME: 18.08.2021

    @Override
    public List<Order> findAll() throws DaoException {
        List<Order> orderList = new ArrayList<>();
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
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
        return Optional.empty();
    }

    @Override
    public boolean create(Order entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Order entity) throws DaoException {
        return false;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        return false;
    }

    @Override
    public Optional<Order> update(Order entity) throws DaoException {
        return Optional.empty();
    }

    private Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Order.Builder builder = Order.newBuilder();
        builder.setID(resultSet.getInt(ORDER_ID))
                .setClientId(resultSet.getInt(ORDER_CLIENT_ID))
                .setCouponId(resultSet.getInt(ORDER_COUPON_ID))
                .setCreated(LocalDateTime.parse(resultSet.getString(ORDER_CREATED), formatter))
                .setDateOfService(LocalDateTime.parse(resultSet.getString(ORDER_DATE_OF_SERVICE), formatter))
                .setIsPaid(resultSet.getBoolean(ORDER_IS_PAID))
                .setIsPaid(resultSet.getBoolean(ORDER_IS_ACTIVE));
        order = builder.build();
        return order;
    }
}
