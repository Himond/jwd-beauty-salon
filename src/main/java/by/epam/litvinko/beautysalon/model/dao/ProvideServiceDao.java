package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;


/**
 * The interface Provide service dao.
 */
public interface ProvideServiceDao {

    /**
     * Find all by category list.
     *
     * @param category the category
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ProvideService> findAllByCategory(String category) throws DaoException;

    /**
     * Find all by order id list.
     *
     * @param order the order
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ProvideService> findAllByOrderId(Order order) throws DaoException;
}
