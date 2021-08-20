package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Cart;
import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CartDaoImpl extends AbstractDao<Integer, Cart> {

    private static Logger logger = LogManager.getLogger();

    private static final String INSERT_ORDER_ITEM = "INSERT INTO order_item(order_id, service_id, master_id) " +
            "VALUES ( ?, ?, ?)";

    @Override
    public List<Cart> findAll() throws DaoException {
        throw new UnsupportedOperationException("Method not allowed");
    }

    @Override
    public Optional<Cart> findById(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Method not allowed");
    }

    @Override
    public boolean create(Cart entity) throws DaoException {
        boolean result = false;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_ORDER_ITEM)){
            for (ProvideService provideService: entity.getService().keySet()){
                statement.setInt(1, entity.getOrderID());
                statement.setInt(2, provideService.getId());
                statement.setInt(3, entity.getService().get(provideService).getId());
                result = statement.executeUpdate() == 1;
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return  result;
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        throw new UnsupportedOperationException("Method not allowed");
    }

    @Override
    public Optional<Cart> update(Cart entity) throws DaoException {
        throw new UnsupportedOperationException("Method not allowed");
    }
}
