package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.ProvideService;
import by.epam.litvinko.beautysalon.entity.ProvideServiceReview;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;

public interface ProvideServiceReviewDao {

    List<ProvideServiceReview> findAllByServiceId(int id) throws DaoException;
}
