package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.*;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.List;


public interface ProvideServiceDao {

    List<ProvideService> findAllByCategory(Category category) throws DaoException;

}
