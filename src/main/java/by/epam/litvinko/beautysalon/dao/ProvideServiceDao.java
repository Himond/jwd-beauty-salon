package by.epam.litvinko.beautysalon.dao;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;


public interface ProvideServiceDao {

    void updateServiceAvailable(Integer serviceId, boolean toggle) throws DaoException;
    List<ProvideService> findAllByCategory(Category category) throws DaoException;
}
