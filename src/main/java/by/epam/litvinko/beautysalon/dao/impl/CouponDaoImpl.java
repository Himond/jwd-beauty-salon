package by.epam.litvinko.beautysalon.dao.impl;

import by.epam.litvinko.beautysalon.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Coupon;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.dao.ColumnName.*;

public class CouponDaoImpl extends AbstractDao<Integer, Coupon> {

    private static Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_COUPON = "SELECT id, code, valid_from, valid_to, " +
            "discount, is_active " +
            "FROM coupon;";

    private static final String SELECT_COUPON_BY_ID = "SELECT id, code, valid_from, valid_to, " +
            "discount, is_active " +
            "FROM coupon " +
            "WHERE id = ?;";

    private static final String INSERT_COUPON = "INSERT INTO coupon(code, valid_from, " +
            "valid_to, discount) " +
            "VALUES (?, ?, ?, ?)";


    private static final String DELETE_COUPON_BY_ID = "DELETE FROM coupon WHERE id = ?;";

    private static final String UPDATE_COUPON_BY_ID = "UPDATE coupon SET code = ?, " +
            "valid_from = ?, valid_to = ?, discount = ?, is_active = ? " +
            "WHERE id = ?;";

    @Override
    public List<Coupon> findAll() throws DaoException {
        List<Coupon> couponList = new ArrayList<>();
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_COUPON)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    Coupon coupon = buildCoupon(resultSet);
                    couponList.add(coupon);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return couponList;
    }

    @Override
    public Optional<Coupon> findById(Integer id) throws DaoException {
        Coupon coupon = null;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(SELECT_COUPON_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    coupon = buildCoupon(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(coupon);
    }

    @Override
    public boolean create(Coupon entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        try (PreparedStatement statement = connection.prepareStatement(INSERT_COUPON)){
            statement.setString(1, entity.getCode());
            statement.setDate(2, Date.valueOf(entity.getValidFrom().toLocalDate()));
            statement.setDate(3, Date.valueOf(entity.getValidTo().toLocalDate()));
            statement.setInt(4, entity.getDiscount());
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
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }

        try(PreparedStatement statement = connection.prepareStatement(DELETE_COUPON_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Coupon> update(Coupon entity) throws DaoException {
        Optional<Coupon> coupon;
        Connection connection = super.connection;
        if (connection == null) {
            throw new DaoException("Connection not established.");
        }
        coupon = findById(entity.getId());
        if (coupon.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_COUPON_BY_ID)){
                statement.setString(1, entity.getCode());
                statement.setDate(2, Date.valueOf(entity.getValidFrom().toLocalDate()));
                statement.setDate(3, Date.valueOf(entity.getValidFrom().toLocalDate()));
                statement.setInt(4, entity.getDiscount());
                statement.setBoolean(5, entity.isActive());
                statement.setInt(6, entity.getId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Coupon id = " + entity.getId() + " don't exist.");
        }
        coupon = findById(entity.getId());
        return coupon;
    }

    private Coupon buildCoupon(ResultSet resultSet) throws SQLException {
        Coupon coupon;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Coupon.Builder builder = Coupon.newBuilder();
        builder.setID(resultSet.getInt(COUPON_ID))
                .setCode(resultSet.getString(COUPON_CODE))
                .setValidFrom(LocalDateTime.parse(resultSet.getString(COUPON_VALID_FROM), formatter))
                .setValidTo(LocalDateTime.parse(resultSet.getString(COUPON_VALID_TO), formatter))
                .setDiscount(resultSet.getInt(COUPON_DISCOUNT))
                .setIsActive(resultSet.getBoolean(COUPON_IS_ACTIVE));
        coupon = builder.build();
        return coupon;
    }

}
