package by.epam.litvinko.beautysalon.model.dao;

import by.epam.litvinko.beautysalon.entity.Master;
import by.epam.litvinko.beautysalon.exception.DaoException;

import java.util.Optional;

public interface MasterDao {
    Optional<Master> findMasterByUserId(Integer id) throws DaoException;
}
