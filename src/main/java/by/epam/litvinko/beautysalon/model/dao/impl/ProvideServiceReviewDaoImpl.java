package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.ProvideServiceReview;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

public class ProvideServiceReviewDaoImpl extends AbstractDao<Integer, ProvideServiceReview> {

    private static Logger logger = LogManager.getLogger();

    private static final String SELECT_ALL_REVIEW = "SELECT id, service_id, client_id, review, " +
            "is_active, salon_service.price, salon_service.service_time " +
            "FROM service_review;";

    private static final String SELECT_REVIEW_BY_ID = "SELECT id, service_id, client_id, review, " +
            "is_active, salon_service.price, salon_service.service_time " +
            "FROM service_review WHERE id = ?;";

    private static final String INSERT_REVIEW = "INSERT INTO service_review(service_id, client_id, review, " +
            "is_active) " +
            "VALUES (?, ?, ?, ?)";


    private static final String DELETE_REVIEW_BY_ID = "DELETE FROM service_review WHERE id = ?;";

    private static final String UPDATE_REVIEW_BY_ID = "UPDATE service_review SET review = ?, " +
            "is_active = ? " +
            "WHERE id = ?;";

    @Override
    public List<ProvideServiceReview> findAll() throws DaoException {
        List<ProvideServiceReview> reviews = new ArrayList<>();
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_REVIEW)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    ProvideServiceReview review = new ProvideServiceReview();
                    review.setId(resultSet.getInt(SERVICE_REVIEW_ID));
                    review.setServiceId(resultSet.getInt(SERVICE_REVIEW_SERVICE_ID));
                    review.setServiceId(resultSet.getInt(SERVICE_REVIEW_CLIENT_ID));
                    review.setReview(resultSet.getString(SERVICE_REVIEW_REVIEW));
                    review.setActive(resultSet.getBoolean(SERVICE_REVIEW_IS_ACTIVE));
                    reviews.add(review);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return reviews;
    }

    @Override
    public Optional<ProvideServiceReview> findById(Integer id) throws DaoException {
        ProvideServiceReview review = null;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_REVIEW_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    review = new ProvideServiceReview();
                    review.setId(resultSet.getInt(SERVICE_REVIEW_ID));
                    review.setServiceId(resultSet.getInt(SERVICE_REVIEW_SERVICE_ID));
                    review.setServiceId(resultSet.getInt(SERVICE_REVIEW_CLIENT_ID));
                    review.setReview(resultSet.getString(SERVICE_REVIEW_REVIEW));
                    review.setActive(resultSet.getBoolean(SERVICE_REVIEW_IS_ACTIVE));
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }

        return Optional.ofNullable(review);
    }

    @Override
    public boolean create(ProvideServiceReview entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW)){
            statement.setInt(1, entity.getServiceId());
            statement.setInt(2, entity.getClientId());
            statement.setString(3, entity.getReview());
            statement.setBoolean(4, entity.isActive());
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
        try(PreparedStatement statement = connection.prepareStatement(DELETE_REVIEW_BY_ID)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<ProvideServiceReview> update(ProvideServiceReview entity) throws DaoException {
        Optional<ProvideServiceReview> review;
        Connection connection = super.connection;
        review = findById(entity.getId());
        if (review.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_REVIEW_BY_ID)){
                statement.setInt(1, entity.getServiceId());
                statement.setInt(2, entity.getClientId());
                statement.setString(3, entity.getReview());
                statement.setBoolean(4, entity.isActive());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Review id = " + entity.getId() + " don't exist.");
        }
        review = findById(entity.getId());
        return review;
    }
}
