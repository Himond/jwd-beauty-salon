package by.epam.litvinko.beautysalon.model.dao.impl;

import by.epam.litvinko.beautysalon.model.dao.AbstractDao;
import by.epam.litvinko.beautysalon.entity.Category;
import by.epam.litvinko.beautysalon.exception.DaoException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.epam.litvinko.beautysalon.model.dao.ColumnName.*;

public class CategoryDaoImpl extends AbstractDao<Integer, Category> {

    private static Logger logger = LogManager.getLogger(CategoryDaoImpl.class);

    private static final String SELECT_ALL_CATEGORY= "SELECT id, name " +
            "FROM salon_category;";


    private static final String SELECT_CATEGORY_BY_ID = "SELECT id, name " +
            "FROM salon_category " +
            "WHERE id = ?;";

    private static final String INSERT_CATEGORY = "INSERT INTO salon_category(name) " +
            "VALUES (?)";

    private static final String DELETE_CATEGORY_BY_NAME = "DELETE FROM salon_category WHERE name = ?;";

    private static final String UPDATE_CATEGORY_BY_ID = "UPDATE category SET name = ?, " +
            "WHERE id = ?;";

    @Override
    public List<Category> findAll() throws DaoException {
        List<Category> categoryList = new ArrayList<>();
        Category category;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CATEGORY)) {
            try (ResultSet resultSet = statement.executeQuery()){
                while (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt(CATEGORY_ID));
                    category.setName(resultSet.getString(CATEGORY_NAME));
                    categoryList.add(category);
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return categoryList;
    }

    @Override
    public Optional<Category> findById(Integer id) throws DaoException {
        Category category = null;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)){
            statement.setInt(1, id);
            statement.executeQuery();
            try (ResultSet resultSet = statement.getResultSet()){
                while (resultSet.next()) {
                    category = new Category();
                    category.setId(resultSet.getInt(CATEGORY_ID));
                    category.setName(resultSet.getString(CATEGORY_NAME));
                }
            }
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return Optional.ofNullable(category);
    }

    @Override
    public boolean create(Category entity) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try (PreparedStatement statement = connection.prepareStatement(INSERT_CATEGORY)){
            statement.setString(1, entity.getName());
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }


    @Override
    public boolean delete(Integer id) throws DaoException {
        boolean result;
        Connection connection = super.connection;
        try(PreparedStatement statement = connection.prepareStatement(DELETE_CATEGORY_BY_NAME)) {
            statement.setInt(1, id);
            result = statement.executeUpdate() == 1;
        } catch (SQLException e) {
            logger.error("Prepare statement cannot be retrieved from the connection.", e);
            throw new DaoException("Prepare statement cannot be retrieved from the connection.", e);
        }
        return result;
    }

    @Override
    public Optional<Category> update(Category entity) throws DaoException {
        Optional<Category> category;
        Connection connection = super.connection;
        category = findById(entity.getId());
        if (category.isPresent()) {
            try (PreparedStatement statement = connection.prepareStatement(UPDATE_CATEGORY_BY_ID)){
                statement.setString(1,entity.getName());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Prepare statement can't be take from connection or unknown field." + e.getMessage());
                throw new DaoException("Prepare statement can't be take from connection or unknown field." + e.getMessage());
            }
        } else {
            logger.info("Category id = " + entity.getId() + " don't exist.");
        }
        category = findById(entity.getId());
        return category;
    }
}
