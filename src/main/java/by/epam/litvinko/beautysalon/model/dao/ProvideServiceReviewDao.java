package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.ProvideServiceReview;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;

/**
 * The interface Provide service review dao.
 */
public interface ProvideServiceReviewDao {

    /**
     * Find all by service id list.
     *
     * @param id the id
     * @return the list
     * @throws DaoException the dao exception
     */
    List<ProvideServiceReview> findAllByServiceId(int id) throws DaoException;
}
