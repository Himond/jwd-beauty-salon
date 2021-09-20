package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.model.dao.ProvideServiceDao;
import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

public class ProvideServiceDaoImpl extends AbstractDao<Integer, ProvideService> implements ProvideServiceDao {

    private static Logger logger = LogManager.getLogger(ProvideServiceDaoImpl.class);

    private static final String SELECT_ALL_SERVICE = "SELECT salon_service.id, salon_service.category_id, salon_service.name, " +
            "salon_service.description, salon_service.price, salon_service.service_time, " +
            "salon_service.available, salon_service.created, salon_service.updated, salon_service.image " +
            "FROM salon_service JOIN salon_category ON salon_service.category_id = salon_category.id;";

    private static final String SELECT_SERVICE_BY_CATEGORY = "SELECT salon_service.id, salon_service.category_id, salon_service.name, " +
            "salon_service.description, salon_service.price, salon_service.service_time, " +
            "salon_service.available, salon_service.created, salon_service.updated, salon_service.image " +
            "FROM salon_service " +
            "JOIN salon_category ON salon_service.category_id = salon_category.id " +
            "WHERE salon_category.name = ?;";

    private static final String SELECT_SERVICE_BY_ORDER_ID = "SELECT salon_service.id, salon_service.category_id, salon_service.name, salon_service.name, " +
            "salon_service.description, salon_service.price, salon_service.service_time, " +
            "salon_service.available, salon_service.created, salon_service.updated, salon_service.image " +
            "FROM salon_service " +
            "JOIN order_item ON salon_service.id = order_item.service_id " +
            "WHERE order_item.order_id = ?;";

    private static final String SELECT_SERVICE_BY_ID = "SELECT salon_service.id, salon_service.category_id, salon_service.name, salon_service.name, " +
            "salon_service.description, salon_service.price, salon_service.service_time, " +
            "salon_service.available, salon_service.created, salon_service.updated, salon_service.image " +
            "FROM salon_service " +
            "JOIN salon_category ON salon_service.category_id = salon_category.id " +
            "WHERE salon_service.id = ?;";

    private static final String INSERT_SERVICE = "INSERT INTO salon_service(category_id, name, description, " +
            "price, service_time, available, created, updated, image) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";


    private static final String DELETE_SERVICE_BY_ID = "DELETE FROM salon_service WHERE id = ?;";

    private static final String UPDATE_SERVICE_BY_ID = "UPDATE salon_service SET category_id = ?, " +
            "name = ?, description = ?, price = ?, service_time = ?, available = ?, created = ?, updated = ?, image = ? " +
            "WHERE id = ?;";

    @Override
    public List<ProvideService> findAll() throws DaoException {
        List<ProvideService> serviceList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_SERVICE)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    ProvideService service = buildProvideService(resultSet);
                    serviceList.add(service);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return serviceList;
    }

    @Override
    public Optional<ProvideService> findById(Integer id) throws DaoException {
        ProvideService service = null;
        Connection connection = super.connection;

        try (PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    service = buildProvideService(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }

        return Optional.ofNullable(service);
    }

    @Override
    public boolean create(ProvideService entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SERVICE)){
            statement.setInt(1, entity.getCategoryId());
            statement.setString(2, entity.getName());
            statement.setString(3, entity.getDescription());
            statement.setBigDecimal(4, entity.getPrice());
            statement.setInt(5, entity.getServiceTime());
            statement.setBoolean(6, entity.isAvailable());
            statement.setDate(7, Date.valueOf(LocalDate.now()));
            statement.setDate(8, Date.valueOf(LocalDate.now()));
            statement.setString(4, entity.getImage());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return  result;
    }


    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        Connection connection = super.connection;

        try(PreparedStatement statement = connection.prepareStatement(DELETE_SERVICE_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<ProvideService> update(ProvideService entity) throws DaoException {
        Optional<ProvideService> service;
        Connection connection = super.connection;
        service = findById(entity.getId());
        if (service.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_SERVICE_BY_ID)){
                statement.setInt(1, entity.getCategoryId());
                statement.setString(2, entity.getName());
                statement.setString(3, entity.getDescription());
                statement.setBigDecimal(4, entity.getPrice());
                statement.setInt(5, entity.getServiceTime());
                statement.setBoolean(6, entity.isAvailable());
                statement.setDate(7, Date.valueOf(entity.getCreated()));
                statement.setDate(8, Date.valueOf(LocalDate.now()));
                statement.setString(4, entity.getImage());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Service id = " + entity.getId() + " don't exist.");
        }
        service = findById(entity.getId());
        return service;
    }

    @Override
    public List<ProvideService> findAllByCategory(String category) throws DaoException {
        List<ProvideService> serviceList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_BY_CATEGORY)) {
            statement.setString(1, category);
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    ProvideService service = buildProvideService(resultSet);
                    serviceList.add(service);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return serviceList;
    }

    @Override
    public List<ProvideService> findAllByOrderId(Order order) throws DaoException {
        List<ProvideService> serviceList = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_SERVICE_BY_ORDER_ID)) {
            statement.setInt(1, order.getId());
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    ProvideService service = buildProvideService(resultSet);
                    serviceList.add(service);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return serviceList;
    }

    private ProvideService buildProvideService(ResultSet resultSet) throws SQLException {
        ProvideService service;
        ProvideService.Builder builder = ProvideService.newBuilder();
        builder.setId(resultSet.getInt(SERVICE_ID))
                .setCategoryId(resultSet.getInt(SERVICE_CATEGORY_ID))
                .setName(resultSet.getString(SERVICE_NAME))
                .setDescription(resultSet.getString(SERVICE_DESCRIPTION))
                .setPrice(resultSet.getBigDecimal(SERVICE_PRICE))
                .setServiceTime(resultSet.getInt(SERVICE_TIME))
                .setAvailable(resultSet.getBoolean(SERVICE_AVAILABLE))
                .setCreated(LocalDate.parse(resultSet.getString(SERVICE_CREATED)))
                .setUpdated(LocalDate.parse(resultSet.getString(SERVICE_UPDATED)))
                .setImage(resultSet.getString(SERVICE_IMAGE));
        service = builder.build();
        return service;
    }


}
